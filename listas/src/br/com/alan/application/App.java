package br.com.alan.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import br.com.alan.runnable.TarefaAdicionarElemento;

public class App {
	
	public static void main(String[] args) throws InterruptedException {
		
		List<String> lista = Collections.synchronizedList(new ArrayList<String>()); //Transforma Para ThreadSafe

        for (int i = 0; i < 10; i++) {
            new Thread(new TarefaAdicionarElemento(lista, i)).start();
        }

        Thread.sleep(2000);

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + " - " + lista.get(i)); //utilizando get(i)
        }
        
        /*Segunda forma de Fazer*/
        
        List<String> lista2 = new Vector<String>(); //VEctor é threadsafe
        
        for (int i = 0; i < 10; i++) {
            new Thread(new TarefaAdicionarElemento(lista2, i)).start();
        }

        Thread.sleep(2000);

        for (int i = 0; i < lista2.size(); i++) {
            System.out.println(i + " - " + lista2.get(i)); 
        }
		
	}

}
