package main.java.psp.ud1.boletin1;

import java.io.IOException;

public class Bol_1 {
    public static void main(String[] args) {
        //Crear el processBuilder con el comando. IMPORTANTE cada argumento del comando es un parametro separado con ,
        ProcessBuilder pb = new ProcessBuilder("ls",  "-l",  "/");

        //Se mete en try & catch porque *1 y *2 pueden dar errores (especificados en los catch)
        try {
            Process p = pb.start(); //*1 se crea el proceso y se empieza
            p.waitFor(); //*2 se espera a que termine de hacer el proceso
            System.out.println(p.exitValue()); //Se devuelve el codigo de salida [ 0: :) | -1: :( ]
        } catch (IOException e) {
            System.err.println("Error al hacer el proceso");
        } catch (InterruptedException ex){
            System.err.println("Error de interrupción");
        }

    }
}
