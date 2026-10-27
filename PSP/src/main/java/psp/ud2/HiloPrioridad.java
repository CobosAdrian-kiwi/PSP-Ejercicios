package main.java.psp.ud2;

public class HiloPrioridad implements Runnable{

    String nombre;

    HiloPrioridad(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public void run() {
        int prioridad = Thread.currentThread().getPriority();
        System.out.println("Soy el hilo: " + nombre + " y esta es mi prioridad: " + prioridad);
    }
}
