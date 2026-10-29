package main.java.psp.ud2.ejercicio4;

import java.util.Random;

public class AdivinoNumero2 implements Runnable{

    private GeneradorNumOculto2 generador;

    int intentoNum;
    int nombre;
    int partidaLocal = 0;


    public AdivinoNumero2(int nombre, GeneradorNumOculto2 generador){
        this.generador = generador;
        this.nombre = nombre;
    }

    @Override
    public void run() {

        Random r = new Random();

        if (partidaLocal == 0){
            partidaLocal = generador.getNumPartida();
        }

        while (true){
            intentoNum = r.nextInt(101);

            switch (generador.esGanador(intentoNum)){
                case 1:
                    System.out.println("Adivino"+nombre + " Ha ganado la partida: " + partidaLocal + " | ("+intentoNum+")");
                case -1:
                    System.out.println("Adivino"+nombre + " ha llegado tarde a la partida: " + partidaLocal + " | ("+intentoNum+")");
                case 2:
                    System.out.println("Adivino"+nombre + " ha intentado adivinar una partida tarde: " + partidaLocal + " | ("+intentoNum+")");
                case 0:
                    System.out.println("Adivino"+nombre + " ha intentado adivinar: " + partidaLocal + " | ("+intentoNum+")");
            }


//            if ((generador.esGanador(intentoNum)) == 1){
//                System.out.println("Se adivinó el numero, era: "+ intentoNum + " | Ganador: Adivino" + nombre);
//                ganador = true;
//
//            } else if ((generador.esGanador(intentoNum)) == -1) {
//                System.out.println("Se falló al adivinar (demasiado tarde) ("+intentoNum+") Adivino"+ nombre);
//                ganador = false;
//
//            }else if ((generador.esGanador(intentoNum)) == 0){
//                System.out.println("Se intentó sin exito ("+intentoNum+") Adivino"+ nombre + " #Partida: " + partidaLocal);
//
//                try {
//                    Thread.sleep(50);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//
//            }else if ((generador.esGanador(intentoNum)) == 2){
//                System.out.println("Se intentó adivinar en una partida que ya ha finalizado: #Partida" + partidaLocal);
//
//                partidaLocal = generador.getNumPartida();
//            }
        }

    }
}
