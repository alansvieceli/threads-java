package br.com.alan.runnable;

import br.com.alan.listas.Lista;

public class TarefaImprimir implements Runnable {

	private Lista lista;

	public TarefaImprimir(Lista lista) {
		this.lista = lista;
	}

	@Override
	public void run() {

		synchronized (lista) { // obtendo a chave da lista
			try {
				System.out.println("esperando, aguardando notificacao");
				lista.wait(); // devolvendo a chave e esperando
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (int i = 0; i < lista.tamanho(); i++) {
				System.out.println(i + " - " + lista.pegaElemento(i));
			}
		}
	}

}
