package br.com.alan.servidor.runnable;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

import br.com.alan.servidor.ServidorTarefas;
import br.com.alan.servidor.comandos.ComandoC1;
import br.com.alan.servidor.comandos.ComandoC2;
import br.com.alan.servidor.comandos.ComandoC3;

public class TarefasDistribuir implements Runnable {

	private Socket socket;
	private ServidorTarefas servidor;
	private ExecutorService threadPool;

	public TarefasDistribuir(ExecutorService threadPool, Socket socket, ServidorTarefas servidor) {
		this.threadPool = threadPool;
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
				                ComandoC1 c1 = new ComandoC1(saidaCliente);
				                this.threadPool.execute(c1);
								break;
							}
							case "c2": {
								saidaCliente.println("Confirmação do comando c2");
				                ComandoC2 c2 = new ComandoC2(saidaCliente);
				                this.threadPool.execute(c2);
								break;
							}
							case "c3": {
								saidaCliente.println("Confirmação do comando c3");
				                ComandoC3 c3 = new ComandoC3(saidaCliente);
				                this.threadPool.execute(c3);
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
