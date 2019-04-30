package br.com.alan.cliente;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 3232);
		try {
			System.out.println("Conexão estabelecida");

			PrintStream saida = new PrintStream(socket.getOutputStream());
			try {
				saida.println("c1");

				
				Scanner teclado = new Scanner(System.in);
				try {
					
					System.out.println("Cliente Parado");
					teclado.nextLine();

				} finally {
					teclado.close();
				}
				
			} finally {
				saida.close();
			}
		} finally {
			socket.close();
		}

	}

}
