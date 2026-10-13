package psp.ud1.boletin1;

import java.io.File;
import java.io.IOException;

public class Bol_3 {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("ls", "-l");

        File listado = new File("listado.txt");

        pb.redirectOutput(listado);
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);

        try {
            Process p = pb.start();
            p.waitFor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
