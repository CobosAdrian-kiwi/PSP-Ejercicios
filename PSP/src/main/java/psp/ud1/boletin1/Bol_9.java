package main.java.psp.ud1.boletin1;

/*
Ejercicio 9 — Analizador de procesos del sistema
Desarrolla un programa Java que ejecute el comando tasklist para obtener los procesos que se están ejecutando en Windows.
El programa deberá:
1. Crear un primer proceso que ejecute tasklist.
2. Obtener mediante un InputStream la salida estándar generada por dicho proceso.
3. Crear un segundo proceso que permita filtrar líneas que contengan un texto determinado, utilizando findstr.
4. Leer línea a línea la salida del primer proceso y enviar cada línea a la entrada estándar del segundo proceso mediante su OutputStream.
5. Cuando se hayan enviado todas las líneas, cerrar correctamente la entrada del segundo proceso.
6. Capturar la salida estándar generada por el segundo proceso.
7. Mostrar cada línea obtenida por pantalla y, simultáneamente, guardarla en un fichero denominado resultado.txt.
8. Esperar a la finalización de ambos procesos y mostrar sus códigos de salida.
 */

import java.io.*;

public class Bol_9 {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("tasklist");
        ProcessBuilder pb2 = new ProcessBuilder("findstr", "java");

        BufferedReader reader = null;
        BufferedWriter writer = null;
        BufferedReader reader2 = null;
        BufferedWriter writer2 = null;

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
            writer2 = new BufferedWriter(new FileWriter("resultado.txt"));

            String linea2;
            while ((linea2 = reader2.readLine()) != null){
                System.out.println(linea2);
                writer2.write(linea2);
                writer2.newLine();
            }

            int p1ExitCode = p1.waitFor();
            int p2ExitCode = p2.waitFor();
            System.out.println("El proceso 1 ha terminado: "+ p1ExitCode);
            System.out.println("El proceso 2 ha terminado: "+ p2ExitCode);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (writer2 != null){
                try {
                    writer2.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
