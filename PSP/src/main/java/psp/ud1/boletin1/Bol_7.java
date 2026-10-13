package main.java.psp.ud1.boletin1;

/*
Ejercicio 7 — Analizador de procesos
Crea un programa Java que ejecute el comando tasklist de Windows. El programa deberá capturar la salida estándar del proceso y leerla línea a línea.
Deberá mostrar únicamente las líneas que contengan el texto java y, al finalizar, indicar cuántos procesos Java se han encontrado.
El programa deberá esperar a que finalice el proceso y mostrar también su código de salida.
 */

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bol_7 {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("tasklist");

        BufferedReader reader = null;

        try {
            Process p = pb.start();
            int acum = 0;

            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String linea;
            while((linea = reader.readLine()) != null){
                if (linea.contains("java")){
                    System.out.println(linea);
                    acum++;
                }
            }

            int salida = p.waitFor();
            System.out.println("Se han encontrado: " + acum + " procesos java");
            System.out.println("Codigo de salida: "+ salida);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
