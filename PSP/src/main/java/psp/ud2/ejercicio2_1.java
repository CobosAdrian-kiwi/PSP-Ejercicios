package main.java.psp.ud2;

public class ejercicio2_1 {
    public static void main(String[] args) {

        Thread current = Thread.currentThread();

        System.out.println("vivo? "+ current.isAlive());
        System.out.println("Nombre: "+ current.getName());
        System.out.println("estado? "+ current.getState());
        System.out.println("daemon? "+ current.isDaemon());
        System.out.println("ID: " + current.threadId());
        System.out.println("Prioridad: "+ current.getPriority());

    }
}
