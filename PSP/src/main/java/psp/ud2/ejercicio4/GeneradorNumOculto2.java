package main.java.psp.ud2.ejercicio4;

import java.util.Random;

public class GeneradorNumOculto2 {

    Random r = new Random();
    private volatile int numOculto;
    private volatile boolean resuelto = false;

    private volatile int numPartida = 0;
    private int maxPartidas = 5;

    public void setResuelto(){
        this.resuelto = !resuelto;
    }

    public synchronized int esGanador(int intento){
        if (intento == numOculto){
            if (!resuelto){
                resuelto = true;
                nuevaPartida();
                return 1;
            }else {
                return -1;
            }
        }else{
            if (resuelto){
                return 2; //Devuelve que la partida ya termino para que deje de comprobar
            }else{
                return 0; //Devuelve que la partida no ha terminado y siga comprobando
            }
        }
    }

    public synchronized void nuevaPartida(){
        numOculto = r.nextInt(101);
        this.resuelto = false;
        if (numPartida++ < maxPartidas){
            numPartida++;
        }
    }

    public boolean getResuelto(){
        return this.resuelto;
    }

    public int getNumPartida() {
        return this.numPartida;
    }

}
