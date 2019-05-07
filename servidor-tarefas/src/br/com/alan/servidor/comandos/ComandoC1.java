package br.com.alan.servidor.comandos;

import java.io.PrintStream;

public class ComandoC1 implements Runnable {

	private PrintStream saidaCliente;

	public ComandoC1(PrintStream saidaCliente) {
		this.saidaCliente = saidaCliente;
	}

	@Override
	public void run() {
		System.out.println("Executando comando c1");
		try {
			Thread.sleep(15000);
			
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		saidaCliente.println("Comando c1 executand com sucesso");
	}

}
