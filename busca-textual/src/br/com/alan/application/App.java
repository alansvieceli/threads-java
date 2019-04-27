package br.com.alan.application;

import br.com.alan.runnable.TarefaBuscaNome;

public class App {

	public static void main(String[] args) {
		
		String nomeBusca = "Jeff";
		
	    Thread threadAutores = new Thread(new TarefaBuscaNome("autores.txt", nomeBusca));
	    Thread threadAssinaturas1 = new Thread(new TarefaBuscaNome("assinaturas1.txt", nomeBusca));
	    Thread threadAssinaturas2 = new Thread(new TarefaBuscaNome("assinaturas2.txt", nomeBusca));
	    
	    threadAutores.start();
	    threadAssinaturas1.start();
	    threadAssinaturas2.start();
		
	}

}
