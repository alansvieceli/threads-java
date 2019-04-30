package br.com.alan.servidor;

import java.net.ServerSocket;
import java.net.Socket;

import br.com.alan.servidor.runnable.TarefaDistribuir;

public class ServidorTarefas {
	
	public static void main(String[] args) throws Exception {
		System.out.println("----- Iniciando Servidor ------");
		
		ServerSocket servidor = new ServerSocket(3232);
		
		
		while (true) {
			Socket socket = servidor.accept();
			new Thread(new TarefaDistribuir(socket)).start();;
			
		}
	}

}
