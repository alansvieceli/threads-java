package br.com.alan.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.alan.servidor.runnable.TarefasDistribuir;

public class ServidorTarefas {
	
	public static void main(String[] args) throws Exception {
		System.out.println("----- Iniciando Servidor ------");		
		ServerSocket servidor = new ServerSocket(3232);		
		
		//ExecutorService threadPool = Executors.newFixedThreadPool(2); // <- pool de  conexões de threads
		ExecutorService threadPool = Executors.newCachedThreadPool(); // cresce e diminui dinamicamente
		
		while (true) {
			Socket socket = servidor.accept();
			threadPool.execute(new TarefasDistribuir(socket));
		
			
		}
	}

}
