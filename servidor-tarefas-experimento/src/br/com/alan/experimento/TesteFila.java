package br.com.alan.experimento;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TesteFila {

	public static void main(String[] args) throws InterruptedException {
		
		
		Queue<String> fila = new LinkedList<>(); // n�o � thread safe
		
		
		fila.offer("c1");
        fila.offer("c2");
        fila.offer("c3");

        System.out.println(fila.poll());
        System.out.println(fila.poll());
        System.out.println(fila.poll());
        System.out.println(fila.poll()); //null
        
        fila.offer("c4");
        fila.offer("c5");
        
        System.out.println(fila.poll());

        System.out.println(fila.size()); //ir� imprimir 0, pois o pool retira os elementos da fila
        
        //--------------------------------------
		BlockingQueue<String> fila2 = new ArrayBlockingQueue<>(3); //� thread safe
		
        fila2.put("c1");
        fila2.put("c2");
        fila2.put("c3");
        fila2.put("c4"); //vai aguardar at� alguem remover um element, pq o m�ximo � 3

        System.out.println(fila2.take());
        System.out.println(fila2.take());
        System.out.println(fila2.take());
        System.out.println(fila2.take()); //bloqueia a threa


        System.out.println(fila2.size()); //ir� imprimir 0, pois o pool retira os elementos da fila
        
        
	}

}
