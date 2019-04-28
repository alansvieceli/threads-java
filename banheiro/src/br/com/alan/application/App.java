package br.com.alan.application;

import br.com.alan.banheiro.Banheiro;
import br.com.alan.runnable.TarefaLimpeza;
import br.com.alan.runnable.TarefaNumero1;
import br.com.alan.runnable.TarefaNumero2;

public class App {

	public static void main(String[] args) {

		Banheiro banheiro = new Banheiro();
		
		//Passando a tarefa e o nome do Thread
		
		Thread limpeza = new Thread(new TarefaLimpeza(banheiro), "Limpeza");
		limpeza.setDaemon(true); //encerra quando todoas dependencias sao encerradas
		/*
		 * Threads daemon são como prestadores de serviços para outras threads. Elas são usadas para dar apoio à tarefas e só 
		 * são necessárias rodar quando as threads "normais" ainda estão sendo executadas. Uma thread daemon não impede a JVM de terminar
		 *  desde que não existem mais threads principais em execução. Um exemplo de uma thread daemon é o coletor de lixo da 
		 *  JVM (Garbage Collector) ou a nossa limpeza do banheiro :)
		 */
		
        Thread convidado1 = new Thread(new TarefaNumero1(banheiro), "João");
        Thread convidado2 = new Thread(new TarefaNumero2(banheiro), "Pedro");
        //Thread convidado3 = new Thread(new TarefaNumero1(banheiro), "Maria");
//        Thread convidado4 = new Thread(new TarefaNumero1(banheiro), "Ana");

        limpeza.start();
        convidado1.start();
        convidado2.start();
//        convidado3.start();
//        convidado4.start();

	}

}
