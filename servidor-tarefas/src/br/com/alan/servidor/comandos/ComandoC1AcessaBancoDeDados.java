package br.com.alan.servidor.comandos;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class ComandoC1AcessaBancoDeDados implements  Callable<String> {

	private PrintStream saidaCliente;

	public ComandoC1AcessaBancoDeDados(PrintStream saidaCliente) {
		this.saidaCliente = saidaCliente;
	}

	@Override
	public String call() throws Exception {
		System.out.println("Executando comando C1-BD");
		saidaCliente.println("Executando comando C1-BD");
		
		Thread.sleep(3000);
		
		Integer numero = new Random().nextInt(78) + 1;
		
		System.out.println("Comando c1 executand com sucesso");
		saidaCliente.println("Comando c1 executand com sucesso");
		
		
		
		return "ComandoC1AcessaBancoDeDados - "+ numero.toString();
	}

}
