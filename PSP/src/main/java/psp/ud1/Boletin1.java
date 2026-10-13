package psp.ud1;


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


import java.io.File;
import java.util.Scanner;

public class Boletin1 {
    public static void main(String[] args) {

        System.out.println("cuentaficheros>");
        Scanner sc = new Scanner(System.in);
        String nombrefich = sc.nextLine();

        while (nombrefich != ""){
            ProcessBuilder pb = new ProcessBuilder("wc", "-lwm");
            pb.redirectInput(new File(nombrefich));
            pb.redirectOutput();
        }

    }
}
