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
		 * Threads daemon s�o como prestadores de servi�os para outras threads. Elas s�o usadas para dar apoio � tarefas e s� 
		 * s�o necess�rias rodar quando as threads "normais" ainda est�o sendo executadas. Uma thread daemon n�o impede a JVM de terminar
		 *  desde que n�o existem mais threads principais em execu��o. Um exemplo de uma thread daemon � o coletor de lixo da 
		 *  JVM (Garbage Collector) ou a nossa limpeza do banheiro :)
		 */
		
        Thread convidado1 = new Thread(new TarefaNumero1(banheiro), "Jo�o");
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
