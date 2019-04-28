package br.com.alan.runnable;

import java.util.List;

public class TarefaAdicionarElemento implements Runnable {

	private List<String> lista;
	private int nroThread;

	public TarefaAdicionarElemento(List<String> lista, int nroThread) {
		this.lista = lista;
		this.nroThread = nroThread;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
            lista.add("Thread " + nroThread + " - " + i);
        }

	}

}
