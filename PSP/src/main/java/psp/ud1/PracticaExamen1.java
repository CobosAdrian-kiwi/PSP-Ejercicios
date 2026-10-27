package main.java.psp.ud1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PracticaExamen1 {
    public static void main(String[] args) {
        File archivo = new File("/home/dam2/IdeaProjects/PSP-Ejercicios/PSP/Texto");

        Scanner sc = new Scanner(System.in);

        try {

            String lineaUsu;
            while (true){
                System.out.println("entrada>");
                lineaUsu = sc.nextLine();

                if (lineaUsu.isBlank()){
                    return;
                }

                ProcessBuilder pb = new ProcessBuilder("grep", lineaUsu);
                pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                pb.redirectError(ProcessBuilder.Redirect.INHERIT);
                pb.redirectInput(archivo);

                Process p = pb.start();

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
