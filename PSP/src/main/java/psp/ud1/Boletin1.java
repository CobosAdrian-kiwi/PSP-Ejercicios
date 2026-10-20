package main.java.psp.ud1;


/*
Ejercicio 1.
Crea un programa que muestre un prompt cuentapalfich> y que, para cada nombre de fichero introducido,
muestre el número de líneas, palabras y caracteres obtenidos mediante una llamada al comando wc.
Para cada nombre de fichero proporcionado, debe dirigirse la entrada estándar de un proceso creado
para el comando wc desde el fichero indicado. La ejecución del programa terminará cuando se introduzca
 una línea vacía. La salida estándar y la salida de error del proceso creado deben redirigirse hacia
 las del proceso padre, es decir, hacia las del programa desarrollado, mediante los métodos redirectOutput
  y redirectError.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Boletin1 {
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
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);

            BufferedReader reader = null;



            try {
                Process p = pb.start();

                p.waitFor();

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {

            }
        }


    }
}
