package main.java.psp.ud2.Boletin1;

/*

Ejercicio 3. Problema de los cinco filósofos
Implementa una solución sencilla al problema de la comida de los 5 filósofos de Dijkstra que evite el interbloqueo.
Cinco filósofos pasan la vida pensando en una mesa redonda, y solo dejan de pensar de vez en cuando para comer de un
cuenco de espaguetis. Para ello, deben tomar los palillos que tienen a ambos lados, primero uno y luego el otro. Por
supuesto, no pueden coger un palillo si lo está utilizando otro filósofo. Entonces, deben esperar a que termine y lo
suelte. Una vez tomado un palillo, no lo sueltan hasta que no consiguen el otro palillo. Entonces, comen y, cuando han
 terminado de comer, dejan ambos palillos de nuevo en la mesa.

Se recomienda numerar a los filósofos de 0 a 4. El filósofo i come con los palillos i (a su izquierda) e (i + 1)
mod 5 (a su derecha).

Debe tenerse especial cuidado para que la solución implementada elimine toda posibilidad de interbloqueo.
¿Cómo lo has hecho?

¿Sería posible, con la solución que has implementado, que algún filósofo no consiguiera comer nunca,
por improbable que fuera? Si es así, explica el escenario en que esto sucedería.


            0   p0  1
                        p1



 */

import java.util.ArrayList;
import java.util.List;

public class Filosofos {
}

class CenaFilosofos {

    enum Estado {
        PENSANDO, HAMBRIENTO, COMIENDO
    }

    // Monitor que gestiona los estados y la sincronización
    static class Mesa {
        private final int numFilosofos;
        private final Estado[] estados;

        public Mesa(int numFilosofos) {
            this.numFilosofos = numFilosofos;
            this.estados = new Estado[numFilosofos];
            for (int i = 0; i < numFilosofos; i++) {
                estados[i] = Estado.PENSANDO;
            }
        }

        private int izquierda(int i) {
            return (i + numFilosofos - 1) % numFilosofos;
        }

        private int derecha(int i) {
            return (i + 1) % numFilosofos;
        }

        public synchronized void tomarTenedores(int id) throws InterruptedException {
            estados[id] = Estado.HAMBRIENTO;
            System.out.println("Filósofo " + id + " tiene hambre.");

            // Espera mientras alguno de sus vecinos esté comiendo
            while (estados[izquierda(id)] == Estado.COMIENDO ||
                    estados[derecha(id)] == Estado.COMIENDO) {
                wait();
            }

            // Ningún vecino come, por lo que toma ambos tenedores a la vez
            estados[id] = Estado.COMIENDO;
            System.out.println("Filósofo " + id + " está COMIENDO.");
        }

        public synchronized void soltarTenedores(int id) {
            estados[id] = Estado.PENSANDO;
            System.out.println("Filósofo " + id + " terminó de comer y vuelve a pensar.");

            // Notifica a todos los hilos en espera para que verifiquen sus vecinos
            notifyAll();
        }
    }

    // Hilo que representa a cada filósofo
    static class Filosofo implements Runnable {
        private final int id;
        private final Mesa mesa;

        public Filosofo(int id, Mesa mesa) {
            this.id = id;
            this.mesa = mesa;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 3; i++) { // Simula 3 ciclos de comida
                    pensar();
                    mesa.tomarTenedores(id);
                    comer();
                    mesa.soltarTenedores(id);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void pensar() throws InterruptedException {
            System.out.println("Filósofo " + id + " está pensando...");
            Thread.sleep((long) (Math.random() * 1000) + 500);
        }

        private void comer() throws InterruptedException {
            Thread.sleep((long) (Math.random() * 1000) + 500);
        }
    }

    public static void main(String[] args) {
        int totalFilosofos = 5;
        Mesa mesa = new Mesa(totalFilosofos);

        for (int i = 0; i < totalFilosofos; i++) {
            Thread hilo = new Thread(new Filosofo(i, mesa));
            hilo.start();
        }
    }
}
