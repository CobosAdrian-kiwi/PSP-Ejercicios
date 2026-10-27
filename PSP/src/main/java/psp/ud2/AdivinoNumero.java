package main.java.psp.ud2;

import java.util.Random;

public class AdivinoNumero implements Runnable{

    private GeneradorNumOculto generador;

    int intentoNum;
    int nombre;

    boolean ganador = false;

    public AdivinoNumero(int nombre, GeneradorNumOculto generador){
        this.generador = generador;
        this.nombre = nombre;
    }

    @Override
    public void run() {

        Random r = new Random();

        while (true){
            intentoNum = r.nextInt(101);

            if ((generador.esGanador(intentoNum)) == 1){
                System.out.println("Se adivinó el numero, era: "+ intentoNum + " | Ganador: Adivino" + nombre);
                ganador = true;
                break;
            } else if ((generador.esGanador(intentoNum)) == -1) {
                System.out.println("Se falló al adivinar (demasiado tarde) ("+intentoNum+") Adivino"+ nombre);
                ganador = false;
                break;
            }else {
                System.out.println("Se intentó sin exito ("+intentoNum+") Adivino"+ nombre);
            }
        }

    }
}
