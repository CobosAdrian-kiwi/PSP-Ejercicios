package main.java.psp.ud1.boletin1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Bol_4 {
    public static void main(String[] args) {

        ProcessBuilder pb = new ProcessBuilder("ps", "aux");
        ProcessBuilder pb2 = new ProcessBuilder("grep", "java");

        try {
            List<Process> ambos = ProcessBuilder.startPipeline(List.of(pb, pb2));
            Process result = ambos.get(ambos.toArray().length - 1);
            String linea;

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(result.getInputStream()));
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea); // Imprime la línea en tu consola de Java
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
