package br.com.alan.runnable;

import br.com.alan.listas.Lista;

public class TarefaAdicionarElemento implements Runnable {

	private Lista lista;
	private int nroThread;

	public TarefaAdicionarElemento(Lista lista, int nroThread) {
		this.lista = lista;
		this.nroThread = nroThread;
	}

	@Override
	public void run() {

		for (int i = 0; i < 100; i++) {
			lista.adicionaElementos("Thread " + nroThread + " - " + i);
		}

	}

}
