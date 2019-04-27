package br.com.alan.runnable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TarefaBuscaNome implements Runnable {

	private String nomeArquivo;
	private String nomeBusca;

	public TarefaBuscaNome(String arquivo, String nomeBusca) {
		this.nomeArquivo = arquivo;
		this.nomeBusca = nomeBusca;
	}

	@Override
	public void run() {
		try {
			Scanner scanner = new Scanner(new File(nomeArquivo));
			try {

				int numeroLinha = 1;

				while (scanner.hasNextLine()) {

					String linha = scanner.nextLine();

					if (linha.toLowerCase().contains(nomeBusca.toLowerCase())) {
						System.out.println(nomeArquivo + " - " + numeroLinha + " - " + linha);
					}

					numeroLinha++;

				}

			} finally {
				scanner.close();
			}

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

	}

}
