package main.java.psp.ud2.Boletin1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Colecta {
    public static void main(String[] args) {

        Colectador colectador = new Colectador();

        final int CANTIDAD_AUMENTADORES = 4;

        List<Thread> aumentadores = new ArrayList<>(List.of());

        for (int i = 0; i < CANTIDAD_AUMENTADORES; i++){
            Thread aumentador = new Thread (new Aumentador("Aumentador" + i,colectador));
            aumentadores.add(aumentador);
        }

        for (Thread aumentador : aumentadores) {
            aumentador.start();
        }
    }
}


class Colectador {
    private int contador;
    private final int MAXIMO = 2000;

    public Colectador(){
        this.contador = 0;
    }

    public synchronized int getContador() {
        return contador;
    }

    public synchronized boolean anadirContador(int cantidad){
        if (contador < MAXIMO) {
            contador += cantidad;
            return true;
        }else {
            return false;
        }
    }
}


class Aumentador implements Runnable {
    private String nombre;
    private int cantidad;

    private final int CANTIDAD_MIN = 4;
    private final int CANTIDAD_MAX = 25;

    private final int TIEMPO_MIN = 10;
    private final int TIEMPO_MAX = 200;

    private Colectador colectador;

    private final Random RANDOM = new Random();

    public Aumentador(String nombre, Colectador colectador){
        this.colectador = colectador;
        this.nombre = nombre;
    }

    @Override
    public void run() {

        while(true){
            try {
                Thread.sleep(RANDOM.nextInt(TIEMPO_MIN, TIEMPO_MAX));

                cantidad = RANDOM.nextInt(CANTIDAD_MIN, CANTIDAD_MAX);
                boolean result = colectador.anadirContador(cantidad);

                if (result) {
                    System.out.println("Hilo: " + nombre + " ha añadido " + cantidad + " exitosamente. | Cantidad del colector actual: " + colectador.getContador());
                }else{
                    System.out.println("Hilo: " + nombre + " NO ha podido añadir " + cantidad + ". | Cantidad del colector actual: " + colectador.getContador());
                    return;
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
