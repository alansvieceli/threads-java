package br.com.alan.servidor.runnable;

import java.net.Socket;
import java.util.Scanner;

public class TarefaDistribuir implements Runnable {

	private Socket socket;

	public TarefaDistribuir(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		System.out.println("Distribuindo tarefas: " + socket);

		try {
			Scanner scanner = new Scanner(socket.getInputStream());
			try {
				while (scanner.hasNextLine()) {
					String linha = scanner.nextLine();
					System.out.println(linha);
				}

			} finally {
				scanner.close();
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
