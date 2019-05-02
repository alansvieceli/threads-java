package br.com.alan.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 3232);
		try {
			System.out.println("Conexão estabelecida");

			Thread threadEnviaComando = new Thread(new Runnable() {

				@Override
				public void run() {

					try {
						enviandoDadosParaServidor(socket);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}

				}

			});

			Thread threadRecebeResposta = new Thread(new Runnable() {

				@Override
				public void run() {

					try {
						recebendoDadosParaServidor(socket);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			});
			
			threadRecebeResposta.start();
			threadEnviaComando.start();
			
			//thread main vai esperar 
			threadEnviaComando.join(); // O método thread.join() faz com que a thread que executa espere até o outro acabar.

		} finally {
			System.out.println("Fechando socket do cliente.");
			socket.close();
		}

	}

	private static void recebendoDadosParaServidor(Socket socket) throws IOException {

		System.out.println("Recebendo dados do servidor!");
		Scanner respostaServidor = new Scanner(socket.getInputStream());
		try {

			while (respostaServidor.hasNextLine()) {
				String linha = respostaServidor.nextLine();
				System.out.println(linha);
			}
		} finally {
			respostaServidor.close();
		}
	}

	private static void enviandoDadosParaServidor(Socket socket) throws IOException {

		System.out.println("Pode enviar comandos!");
		PrintStream saida = new PrintStream(socket.getOutputStream());
		try {
			Scanner teclado = new Scanner(System.in);
			try {

				while (teclado.hasNextLine()) {
					String linha = teclado.nextLine();

					if (linha.trim().equals("")) {
						break;
					}

					saida.println(linha);
				}

			} finally {
				teclado.close();
			}

		} finally {
			saida.close();
		}
	}

}
