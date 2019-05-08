package br.com.alan.servidor.comandos;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class ComandoC2ChamaWS implements Callable<String> {

	private PrintStream saidaCliente;

	public ComandoC2ChamaWS(PrintStream saidaCliente) {
		this.saidaCliente = saidaCliente;
	}

	@Override
	public String call() throws Exception {
		System.out.println("Executando comando C2-WS");
		saidaCliente.println("Executando comando C2-WS");
		
		Thread.sleep(5000);
		
		Integer numero = new Random().nextInt(78) + 1;
		
		System.out.println("Comando c2 executand com sucesso");
		saidaCliente.println("Comando c2 executand com sucesso");
		
		
		
		return "ComandoC2ChamaWS - "+ numero.toString();
	}
}
