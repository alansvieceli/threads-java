package br.com.alan.cliente;

import java.net.Socket;

public class ClienteTarefas {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 3232);
		try {
			System.out.println("Conexão estabelecida");
		} finally {
			socket.close();
		}

	}

}
