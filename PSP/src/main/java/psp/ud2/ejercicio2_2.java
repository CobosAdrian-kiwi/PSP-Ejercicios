package main.java.psp.ud2;

public class ejercicio2_2 {
    public static void main(String[] args) {

        Thread hilo1 = new Thread(new HiloPrioridad("1"));
        Thread hilo2 = new Thread(new HiloPrioridad("2"));

        hilo1.setPriority(Thread.MIN_PRIORITY);
        hilo2.setPriority(Thread.MAX_PRIORITY);

        System.out.println("Ejecucion de hilos:");

        hilo1.start();
        hilo2.start();

    }
}
