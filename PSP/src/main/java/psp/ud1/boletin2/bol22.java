package main.java.psp.ud1.boletin2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class bol22 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println("cuentaficheros>");
            String nombrefich = sc.nextLine();

            if (nombrefich == ""){
                System.out.println("Finalizando...");
                break;
            }

            String comando = "wc -l -w -m '" + nombrefich + "' | awk '{print $1, $2, $3}'";

            ProcessBuilder pb = new ProcessBuilder("bash", "-c", comando);

            pb.redirectError(ProcessBuilder.Redirect.INHERIT);

            BufferedReader reader = null;

            try {
                Process p = pb.start();

                reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

                String linea = reader.readLine();

                p.waitFor();

                if (linea != null){
                    String[] datos = linea.trim().split("\\s+");

                    if (datos.length >= 3){
                        String lineasTotal = datos[0];
                        String palabrasTotal = datos[1];
                        String caracteresTotal = datos[2];

                        System.out.println("Líneas totales: "+ lineasTotal);
                        System.out.println("Palabras totales: " + palabrasTotal);
                        System.out.println("Caracteres totales: "+ caracteresTotal);
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }

}
