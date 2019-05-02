package br.com.alan.cliente.comunicacao;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import br.com.alan.cliente.log.Log;

public class Comunicador {

	private Socket socket;
	private PrintStream saida;

	public Comunicador() {

		try {
			this.socket = new Socket("localhost", 3232);
			this.saida = new PrintStream(this.socket.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					recebendoDadosParaServidor();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}).start();

	}

	public void fechar() {
		try {
			this.saida.close();
			this.socket.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void recebendoDadosParaServidor() throws IOException {
		Scanner respostaServidor = new Scanner(socket.getInputStream());
		try {

			while (respostaServidor.hasNextLine()) {
				String linha = respostaServidor.nextLine();
				Log.gravarLog("Recebendo: " + linha);
			}
		} finally {
			respostaServidor.close();
		}
	}

	public void enviandoDadosParaServidor(String linha) throws IOException {
		Log.gravarLog("Enviando: " + linha);
		saida.println(linha);

	}

}
