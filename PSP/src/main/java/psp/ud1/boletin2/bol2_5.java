package main.java.psp.ud1.boletin2;

import java.io.*;
import java.util.Scanner;

public class bol2_5 {
    public static void main(String[] args) {
        String expr = (args.length > 0) ? args[0] : "";
        Scanner sc = new Scanner(System.in);

        ProcessBuilder pb = null;
        Process p = null;

        OutputStream os = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {

            String lineausu;
            while (true){
                pb = new ProcessBuilder("grep", expr);
                p = pb.start();

                os = p.getOutputStream();
                osw = new OutputStreamWriter(os);
                bw = new BufferedWriter(osw);

                is = p.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                System.out.println("Introducir linea:");
                lineausu = sc.nextLine();

                if (lineausu.isEmpty()){
                    break;
                }

                bw.write(lineausu);
                bw.newLine();
                bw.flush();
                bw.close();

                if (br.readLine() == null){
                    System.out.println(lineausu + " no contiene la expresion: " + expr);
                }else {
                    System.out.println(lineausu + " contiene la expresion: "+ expr);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
