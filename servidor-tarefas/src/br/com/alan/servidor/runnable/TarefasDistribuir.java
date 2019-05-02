package br.com.alan.servidor.runnable;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TarefasDistribuir implements Runnable {

	private Socket socket;

	public TarefasDistribuir(Socket socket) {
		this.socket = socket;
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
								break;
							}
							case "c2": {
								saidaCliente.println("Confirmação do comando c2");
								break;
							}
							case "c3": {
								saidaCliente.println("Confirmação do comando c3");
								break;
							}
							default: {
								saidaCliente.println("Comando não tratado");
								break;
							}
						}

						System.out.println(linha);
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
