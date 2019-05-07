package br.com.alan.servidor.comandos;

import java.io.PrintStream;

public class ComandoC2 implements Runnable {

	private PrintStream saidaCliente;

	public ComandoC2(PrintStream saidaCliente) {
		this.saidaCliente = saidaCliente;
	}

	@Override
	public void run() {
		System.out.println("Executando comando c2");
		try {
			Thread.sleep(17000);
			
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		saidaCliente.println("Comando c2 executand com sucesso");
		
	}

}
