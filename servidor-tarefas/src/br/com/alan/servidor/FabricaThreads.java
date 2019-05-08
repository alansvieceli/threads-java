package br.com.alan.servidor;

import java.util.concurrent.ThreadFactory;

import br.com.alan.exceptions.TratadorDeExcecao;

public class FabricaThreads implements ThreadFactory {

	private static Integer numero = 1;

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r, "Thread Servidor Tarefas - " + numero);
		numero++;		
		thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
		return thread;
	}

}
