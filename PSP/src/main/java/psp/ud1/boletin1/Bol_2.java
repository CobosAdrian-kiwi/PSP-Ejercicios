package psp.ud1.boletin1;

import java.io.IOException;

public class Bol_2 {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("sleep", "6"); //Proceso de esperar 6 segundos

        try {
            Process p = pb.start(); //Se inicia el proceso

            for (int i=0; i < 7; i++){ //Se repite 7 veces

                //Cada vez se comprueba y muestra si está en ejecucion (true) o no (false)
                System.out.println(p.isAlive());
                Thread.sleep(1000); //Espera 1 sec

            }
            System.out.println(p.exitValue()); //Devuelve valor de salida

            System.out.println("Programa terminado");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
