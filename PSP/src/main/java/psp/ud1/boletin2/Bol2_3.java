package main.java.psp.ud1.boletin2;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Bol2_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println("cuentapalenlin>");
            String lineausu = sc.nextLine();

            if (lineausu == ""){
                break;
            }

            ProcessBuilder pb = new ProcessBuilder("wc", "-w", "-m");


            try {
                Process p = pb.start();



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
