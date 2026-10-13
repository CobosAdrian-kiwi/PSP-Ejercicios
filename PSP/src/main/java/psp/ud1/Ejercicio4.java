package psp.ud1;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Ejercicio4 {
    public static void main(String[] args) throws IOException, InterruptedException {

        File salida = new File("salida-ej4.txt");

        ProcessBuilder pb1 = new ProcessBuilder("grep", "^video", "Entrada-ej4.txt");
        ProcessBuilder pb2 = new ProcessBuilder("sed", "s/\\t\\t*/:/g");
        pb2.redirectOutput(salida);

        List<Process> ambos = ProcessBuilder.startPipeline(List.of(pb1, pb2));

    }
}
