package br.com.alan.listas;

public class Lista {

	private String[] elementos = new String[1000];
	private int indice = 0;

	public synchronized void adicionaElementos(String elemento) {
		this.elementos[indice] = elemento;
		this.indice++;
		
		/*
		synchronized(this) {
			this.elementos[indice] = elemento;
			this.indice++;			
		}
		*/
		
	}

	public int tamanho() {
		return this.elementos.length;
	}

	public String pegaElemento(int posicao) {
		return this.elementos[posicao];
	}

}
