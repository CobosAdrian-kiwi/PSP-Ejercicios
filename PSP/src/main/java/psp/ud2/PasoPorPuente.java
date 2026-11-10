package main.java.psp.ud2;

import java.util.Random;

class Puente {  // Estado, objeto compartido entre los hilos
    private static final int PESO_MAXIMO = 200;
    private static final int MAX_PERSONAS = 4;
    private static final int MAX_SENTIDO = 3;
    private int peso = 0;
    private int numPersonas = 0;
    private int numSentido1 = 0;
    private int numSentido2 = 0;

    synchronized public int getPeso() {
        return peso;
    }

    synchronized public int getNumPersonas() {
        return numPersonas;
    }

    synchronized public int getNumSentido1(){
        return numSentido1;
    }

    synchronized public int getNumSentido2(){
        return numSentido2;
    }

    synchronized public boolean autorizacionPaso(Persona persona) {
        boolean result;
        if (this.peso + persona.getPeso() <= Puente.PESO_MAXIMO && this.numPersonas < Puente.MAX_PERSONAS
            && ((persona.getSentido()) ? numSentido1 : numSentido2) < MAX_SENTIDO ) {
            this.numPersonas++;
            this.peso += persona.getPeso();
            if (persona.getSentido()){
                numSentido1++;
            }else {
                numSentido2++;
            }
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    synchronized public void terminaPaso(Persona persona) {
        this.peso -= persona.getPeso();
        this.numPersonas--;

        if (persona.getSentido()){
            numSentido1--;
        }else {
            numSentido2--;
        }
    }
}

class Persona implements Runnable {
    private final String idPersona;
    private final int peso;
    private final int tMinPaso, tMaxPaso;
    private final Puente puente;
    private boolean sentido;

    public int getPeso() {
        return peso;
    }

    public boolean getSentido() {
        return this.sentido;
    }

    Persona(Puente puente, int peso, int tMinPaso, int tMaxPaso, String idP, boolean sentido) {
        this.puente = puente;
        this.peso = peso;
        this.tMinPaso = tMinPaso;
        this.tMaxPaso = tMaxPaso;
        this.idPersona = idP;
        this.sentido = sentido;
    }

    @Override
    public void run() {
        System.out.printf("> %s (%d kg) quiere cruzar en sentido %b. Estado puente: %d kg, %d pers. (S1) / %d pers. (S2).\n",
                this.idPersona,
                this.peso,
                this.sentido,
                this.puente.getPeso(),
                this.puente.getNumSentido1(),
                this.puente.getNumSentido2());

        // Espera para conseguir autorización
        boolean autorizado = false;
        while (!autorizado) {
            synchronized (this.puente) {
                autorizado = this.puente.autorizacionPaso(this);
                if (!autorizado) {
                    try {
                        System.out.printf("# %s debe esperar.\n", this.idPersona);
                        this.puente.wait();
                    } catch (InterruptedException ex) {
                        System.out.printf("Interrupción mientras %s espera.\n", this.idPersona);
                    }
                }
            }
        }

        System.out.printf("> %s (%d kg) entra al puente en sentido %b. Peso total: %d kg | Sentido 1: %d | Sentido 2: %d\n",
                this.idPersona,
                this.getPeso(),
                this.sentido,
                this.puente.getPeso(),
                this.puente.getNumSentido1(),
                this.puente.getNumSentido2());

        Random r = new Random();  // Pasa al puente, y tarda un tiempo en cruzar
        int tiempoPaso = this.tMinPaso + r.nextInt(this.tMaxPaso - this.tMinPaso + 1);
        try {
            System.out.printf("%s va a tardar tiempo %d en cruzar.\n", this.idPersona, tiempoPaso);
            Thread.sleep(1000 * tiempoPaso);
        } catch (InterruptedException ex) {
            System.out.printf("Interrupción mientras %s pasa.\n", this.idPersona);
        }

        // Sale del puente
        synchronized (this.puente) {
            this.puente.terminaPaso(this);
            System.out.printf("< %s sale del puente. Peso restante: %d kg | Quedan -> Sentido 1: %d, Sentido 2: %d.\n",
                    this.idPersona,
                    this.puente.getPeso(),
                    this.puente.getNumSentido1(),
                    this.puente.getNumSentido2());
            puente.notifyAll();
        }
    }
}

public class PasoPorPuente {

    public static void main(String[] args) {
        final Puente puente = new Puente();

        int tMinParaLlegadaPersona = 1;
        int tMaxParaLlegadaPersona = 30;

        int tMinPaso = 10;
        int tMaxPaso = 50;
        int minPesoPersona = 40;
        int maxPesoPersona = 120;

        System.out.println(">>>>>>>>>>>> Comienza simulación.");
        Random r = new Random();
        int idPersona = 1;

        while (true) {
            int tParaLlegadaPersona = (tMinParaLlegadaPersona + r.nextInt(tMaxParaLlegadaPersona - tMinParaLlegadaPersona + 1));
            int pesoPersona = minPesoPersona + r.nextInt(maxPesoPersona - minPesoPersona + 1);

            boolean sentido = Math.random() < 0.5;

            System.out.printf("Siguiente persona llega en %d segundos.\n", tParaLlegadaPersona);
            try {
                Thread.sleep(1000 * tParaLlegadaPersona);
            } catch (InterruptedException ex) {
                System.out.printf("Interrumpido proceso principal");
            }

            Thread hiloPersona = new Thread(new Persona(puente, pesoPersona, tMinPaso, tMaxPaso, "P" + idPersona, sentido));
            hiloPersona.start();

            idPersona++;
        }
    }
}
