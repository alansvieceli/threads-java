package br.com.alan.servidor.comandos;

import java.io.PrintStream;

public class ComandoC3 implements Runnable {

	private PrintStream saidaCliente;

	public ComandoC3(PrintStream saidaCliente) {
		this.saidaCliente = saidaCliente;
	}

	@Override
	public void run() {
		System.out.println("Executando comando c3");
		try {
			Thread.sleep(7000);
			
			throw new RuntimeException("Erro controlado");
			
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		//saidaCliente.println("Comando c3 executand com sucesso");
		
	}

}
