package main.java.psp.ud2.Boletin1;

/*
Ejercicio 2. Depósitos de pintura
Se tienen 3 depósitos de pintura, cada uno de un color primario,
a saber: cian, magenta y amarillo. Se tienen 3 personas que intentan
preparar, cada una, pintura de un color secundario distinto. Estos
son rojo (mezcla de amarillo y magenta), azul (mezcla de magenta y cian)
 y verde (mezcla de amarillo y cian). Para preparar un color
 secundario, una persona debe tener acceso en exclusiva a los dos
 colores primarios necesarios.

Cada persona debe implementarse mediante un hilo que, en un bucle
sin fin, haga lo siguiente: obtener acceso en exclusiva al depósito
 de los dos colores primarios que necesita, mantenerlo durante un
 tiempo aleatorio (por ejemplo, entre 100 y 500 ms) y esperar un
 tiempo aleatorio antes de volver a intentar preparar de nuevo el
 color, que podría estar, por ejemplo, entre 1 y 2 s.

Hay que tener especial cuidado en realizar el bloqueo de los
depósitos de pintura (como se ha dicho, uno por cada color primario),
de manera que no se pueda nunca producir un interbloqueo. Para una
prueba más exhaustiva, se puede probar este programa, sin pausas para
 el tiempo de estudio y para el tiempo de descanso, durante varios segundos.
 */

import java.util.Random;

public class DepositosPintura {
    boolean ocupadoCian;
    boolean ocupadoMagenta;
    boolean ocupadoAmarillo;

    public DepositosPintura(){
        this.ocupadoAmarillo = false;
        this.ocupadoCian = false;
        this.ocupadoMagenta = false;
    }

    public synchronized boolean isOcupadoCian(){
        return ocupadoCian;
    }
    public synchronized boolean isOcupadoMagenta(){
        return ocupadoMagenta;
    }
    public synchronized boolean isOcupadoAmarillo(){
        return ocupadoAmarillo;
    }

    public synchronized boolean accesoCian(){
        if (ocupadoCian){
            return false;
        }else {
            ocupadoCian = true;
            return true;
        }
    }

    public synchronized boolean accesoMagenta(){
        if (ocupadoMagenta){
            return false;
        }else {
            ocupadoMagenta = true;
            return true;
        }
    }

    public synchronized boolean accesoAmarillo() {
        if (ocupadoAmarillo){
            return false;
        }else {
            ocupadoAmarillo = true;
            return true;
        }
    }
}

class Pintor implements Runnable{

    public enum Color {
        Cian,
        Magenta,
        Amarillo
    }

    private Color color1;
    private Color color2;
    private boolean color1Comprobante = false;
    private boolean color2Comprobante = false;

    private final int TIEMPO_MIN = 100;
    private final int TIEMPO_MAX = 500;

    private final int TIEMPO_ESPERA_MIN = 1000;
    private final int TIEMPO_ESPERA_MAX = 3000;

    private boolean mezclaHecha;

    private Random random = new Random();

    private DepositosPintura deposito;

    public Pintor(Color color1, Color color2, DepositosPintura deposito){
        this.color1 = color1;
        this.color2 = color2;
        this.deposito = deposito;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(random.nextInt(TIEMPO_ESPERA_MIN, TIEMPO_ESPERA_MAX));



            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

























