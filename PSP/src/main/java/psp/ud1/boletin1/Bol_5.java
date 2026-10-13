package main.java.psp.ud1.boletin1;

import java.io.*;

public class Bol_5 {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("tr", "a-z", "A-Z");

        Process p = null;
        BufferedWriter writer = null;
        try {
            p = pb.start();


            //Se le pasa el texto al proceso
            writer = new BufferedWriter(
                    new OutputStreamWriter(p.getOutputStream()));

            writer.write("hola\n");
            writer.write("caracola\n");
            writer.write("pato de goma\n");

            writer.close(); //IMPORTANTE se cierra el writer


            //Se muestra por consola con el Reader
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));

            System.out.println("Salida del proceso:");
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

            p.waitFor();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    System.err.println("Error al cerrar el writer: " + ex.getMessage());
                }
            }
        }
    }
}
