package main.java.psp.ud2;

import java.util.Random;

public class GeneradorNumOculto {

    Random r = new Random();
    private volatile int numOculto = r.nextInt(101);
    private volatile boolean resuelto = false;

    public void setResuelto(){
        this.resuelto = !resuelto;
    }

    public synchronized int esGanador(int intento){
        if (intento == numOculto){
            if (!resuelto){
                resuelto = true;
                return 1;
            }else {
                return -1;
            }
        }else{
            return 0;
        }
    }

    public boolean getResuelto(){
        return this.resuelto;
    }
}
