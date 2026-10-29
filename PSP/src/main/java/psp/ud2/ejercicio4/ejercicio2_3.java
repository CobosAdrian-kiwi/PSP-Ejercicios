package main.java.psp.ud2.ejercicio4;

import java.util.ArrayList;
import java.util.List;

public class ejercicio2_3 {
    public static void main(String[] args) {

        int cantidadAdivinos = 10;

        GeneradorNumOculto2 generador = new GeneradorNumOculto2();

        List<Thread> adivinos = new ArrayList<>(List.of());

        for (int i=0; i < cantidadAdivinos; i++){
            String nombreHilo = "Adivino" + i;
            Thread hilo = new Thread(new AdivinoNumero2(i+1, generador), nombreHilo);
            adivinos.add(hilo);
        }

        generador.nuevaPartida();

        for (Thread adivino : adivinos){
            adivino.start();
        }


    }
}
