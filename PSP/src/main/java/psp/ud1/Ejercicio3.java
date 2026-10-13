package psp.ud1;

/*
Crea un programa que ejecute el comando grep para seleccionar las
líneas de un fichero que contengan un texto, y que las escriba en otro fichero.
El texto, el fichero en el que se buscan las líneas que contienen el texto y el
fichero en el que se escriben las líneas que contienen el texto se pasan como
argumentos de línea de comandos. Puedes desarrollar el programa a partir del
primer programa de ejemplo. Solo hay que utilizar adecuadamente los métodos
redirectInput y redirectError.
 */

import java.io.File;
import java.io.IOException;

public class Ejercicio3 {
    public static void main(String[] args) {

        String texto = "en";

        File salida = new File("salida.txt");
        File entrada = new File("Texto");

        ProcessBuilder pb = new ProcessBuilder("grep", texto, entrada.getPath());
        pb.redirectOutput(salida);
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);

        Process p = null;
        try {
            p = pb.start();
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            System.err.println("Error: "+e.getMessage());

        }

        System.out.println("Se han pasado las lineas correctamente");
    }
}