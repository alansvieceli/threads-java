package br.com.alan.application;

import br.com.alan.listas.Lista;
import br.com.alan.runnable.TarefaAdicionarElemento;
import br.com.alan.runnable.TarefaImprimir;

public class App {

	public static void main(String[] args) throws InterruptedException {
		Lista lista = new Lista();
		
		for (int i = 0; i < 10; i++) {
			new Thread(new TarefaAdicionarElemento(lista, i)).start();
		}
		
		new Thread(new TarefaImprimir(lista)).start();

	}

}
