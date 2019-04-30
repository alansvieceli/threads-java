package br.com.alan.servidor.runnable;

import java.net.Socket;

public class TarefaDistribuir implements Runnable {

	private Socket socket;

	public TarefaDistribuir(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		System.out.println("Distribuindo tarefas: " + socket);

		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Acabou");

	}

}
