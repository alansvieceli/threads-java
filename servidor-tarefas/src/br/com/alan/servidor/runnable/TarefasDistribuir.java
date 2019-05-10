package br.com.alan.servidor.runnable;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import br.com.alan.servidor.ServidorTarefas;
import br.com.alan.servidor.comandos.ComandoC1AcessaBancoDeDados;
import br.com.alan.servidor.comandos.ComandoC2ChamaWS;
import br.com.alan.servidor.comandos.ComandoC3;

public class TarefasDistribuir implements Runnable {

	private Socket socket;
	private ServidorTarefas servidor;
	private ExecutorService threadPool;
	private BlockingQueue<String> filaComandos;

	public TarefasDistribuir(ExecutorService threadPool, BlockingQueue<String> filaComandos, Socket socket, ServidorTarefas servidor) {
		this.threadPool = threadPool;
		this.filaComandos = filaComandos;
		this.socket = socket;
		this.servidor = servidor;
	}

	@Override
	public void run() {

		System.out.println("Distribuindo tarefas: " + socket);

		try {
			Scanner entradaCliente = new Scanner(socket.getInputStream());
			try {

				PrintStream saidaCliente = new PrintStream(socket.getOutputStream());
				try {
					while (entradaCliente.hasNextLine()) {
						String linha = entradaCliente.nextLine();
						System.out.println("Comando recebido: " + linha);

						switch (linha) {
							case "c1": {
								saidaCliente.println("Confirmação do comando c1");
				                ComandoC1AcessaBancoDeDados c1 = new ComandoC1AcessaBancoDeDados(saidaCliente);
				                this.threadPool.submit(c1);
								break;
							}
							case "c2": {
								saidaCliente.println("Confirmação do comando c2");
				                ComandoC2ChamaWS c2 = new ComandoC2ChamaWS(saidaCliente);
				                ComandoC1AcessaBancoDeDados c1 = new ComandoC1AcessaBancoDeDados(saidaCliente);
				                
				                Future<String> ftC2 = this.threadPool.submit(c2);
				                Future<String> ftC1 = this.threadPool.submit(c1);											                
				                
				                this.threadPool.submit(new JuntaResultadosFutureWSFutureBanco(ftC2, ftC1, saidaCliente));
				                
				                break;
							}
							case "c3": {
								saidaCliente.println("Confirmação do comando c3");
				                ComandoC3 c3 = new ComandoC3(saidaCliente);
				                this.threadPool.execute(c3);
								break;
							}
							case "c4": {
							    this.filaComandos.put(linha); //lembrando, bloqueia se tiver cheia
							    saidaCliente.println("Comando c4 adicionado na fila");
								break;
							}							
							case "fim": {
								saidaCliente.println("Desligando servidor");
								this.servidor.parar();
								break;
							}
							default: {
								saidaCliente.println("Comando não tratado");
								break;
							}
						}
					}

					System.out.println("saindo do servidor");
				} finally {
					saidaCliente.close();
				}

			} finally {
				entradaCliente.close();
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
