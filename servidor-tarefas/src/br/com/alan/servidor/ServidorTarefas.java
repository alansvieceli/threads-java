package br.com.alan.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import br.com.alan.servidor.runnable.TarefasDistribuir;

public class ServidorTarefas {

	private ServerSocket servidor;
	private ExecutorService threadPool;
	//private boolean estaRodando;
	//private volatile boolean estaRodando;
	private AtomicBoolean estaRodando;

	public static void main(String[] args) throws Exception {

		ServidorTarefas servidor = new ServidorTarefas();
		try {
			servidor.rodar();
		} finally {
			servidor.parar();
		}

	}

	public ServidorTarefas() throws IOException {
		System.out.println("----- Iniciando Servidor ------");
		this.estaRodando = new AtomicBoolean(true);
		this.servidor = new ServerSocket(3232);
		 this.threadPool = Executors.newFixedThreadPool(6, new FabricaThreads()); // <- pool de
		//this.threadPool = Executors.newCachedThreadPool(); // cresce e diminui dinamicamente
	}

	public void parar() throws IOException {
		this.estaRodando.set(false);
		this.threadPool.shutdown();
		this.servidor.close();
	}

	public void rodar() throws IOException {
		while (this.estaRodando.get()) {
			try {
			Socket socket = servidor.accept();
			threadPool.execute(new TarefasDistribuir(this.threadPool, socket, this));
			} catch (SocketException e) {
												
			}

		}

	}

}
