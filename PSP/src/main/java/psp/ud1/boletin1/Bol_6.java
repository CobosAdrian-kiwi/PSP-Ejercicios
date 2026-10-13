package psp.ud1.boletin1;

import java.io.File;
import java.io.IOException;

public class Bol_6 {
    public static void main(String[] args) {
        File entrada = new File("Texto");
        File salida = new File("Bol1-6-log.txt");

        ProcessBuilder pb = new ProcessBuilder("grep", "ERROR");
        pb.redirectInput(entrada);
        pb.redirectOutput(salida);

        try {
            Process p = pb.start();

            int exitCode = p.waitFor();
            System.out.println("Proceso terminado con codigo: " + exitCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
