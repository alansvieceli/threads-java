package br.com.alan.runnable;

import br.com.alan.banheiro.Banheiro;

public class TarefaLimpeza implements Runnable {

	private Banheiro banheiro;

	public TarefaLimpeza(Banheiro banheiro) {
		this.banheiro = banheiro;
	}

	public void run() {
		this.banheiro.limpa();
	}

}
