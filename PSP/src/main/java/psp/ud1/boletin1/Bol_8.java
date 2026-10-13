package main.java.psp.ud1.boletin1;

/*
Ejercicio 8 — Dos procesos comunicados desde Java
Crea un programa Java que ejecute un primer proceso que obtenga el listado de procesos del sistema mediante tasklist.
El programa Java deberá capturar la salida estándar del primer proceso y leerla línea a línea.
Cada línea obtenida deberá enviarse a la entrada estándar de un segundo proceso encargado de buscar aquellas líneas que contengan el texto java.
La salida producida por el segundo proceso deberá ser leída desde Java y mostrada por pantalla.
 */

import java.io.*;

public class Bol_8 {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("tasklist");
        ProcessBuilder pb2 = new ProcessBuilder("findstr", "java");

        BufferedReader reader = null;
        BufferedWriter writer = null;
        BufferedReader reader2 = null;

        try{
            Process p1 = pb.start();
            Process p2 = pb2.start();

            reader = new BufferedReader(new InputStreamReader(p1.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(p2.getOutputStream()));

            String linea;
            while ((linea = reader.readLine()) != null){
                writer.write(linea);
                writer.newLine();
            }

            writer.close();
            reader.close();

            reader2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));

            String linea2;
            while ((linea2 = reader2.readLine()) != null){
                System.out.println(linea2);
            }

            p1.waitFor();
            p2.waitFor();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
