package main.java.psp.ud2.ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class ejercicio2_3 {
    public static void main(String[] args) {

        int cantidadAdivinos = 10;

        GeneradorNumOculto generador = new GeneradorNumOculto();

        List<Thread> adivinos = new ArrayList<>(List.of());

        for (int i=0; i < cantidadAdivinos; i++){
            String nombreHilo = "Adivino" + i;
            Thread hilo = new Thread(new AdivinoNumero(i+1, generador), nombreHilo);
            adivinos.add(hilo);
        }

        for (Thread adivino : adivinos){
            adivino.start();
        }
    }
}
