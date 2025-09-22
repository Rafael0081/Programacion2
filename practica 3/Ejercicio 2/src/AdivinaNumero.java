import java.util.Random;
import java.util.Scanner;

public class AdivinaNumero extends Juego {
    private int numeroAAdivinar;
    private Scanner sc = new Scanner(System.in);

    public AdivinaNumero(int numeroDeVidas) {
        super(numeroDeVidas);
    }

    /** Valida que el número esté entre 0 y 10 */
    public boolean validaNumero(int n) {
        return n >= 0 && n <= 10;
    }

    /** Lógica principal del juego */
    public void juega() {
        reiniciaPartida();
        Random rand = new Random();

        // Generar el número según el tipo de juego
        if (this instanceof AdivinaPar) {
            // Solo números PARES
            do {
                numeroAAdivinar = rand.nextInt(11);
            } while (numeroAAdivinar % 2 != 0);
        } else if (this instanceof AdivinaImpar) {
            // Solo números IMPARES
            do {
                numeroAAdivinar = rand.nextInt(11);
            } while (numeroAAdivinar % 2 == 0);
        } else {
            // Juego normal
            numeroAAdivinar = rand.nextInt(11);
        }

        System.out.println("\n--- " + this.getClass().getSimpleName() + " ---");
        System.out.println("Adivina un número entre 0 y 10.");

        // Bucle principal de juego
        while (true) {
            System.out.print("Ingresa un entero (0-10): ");
            int n;
            try {
                n = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, ingresa un entero.");
                continue;
            }

            if (!validaNumero(n)) {
                System.out.println("Número fuera de rango.");
                continue;
            }

            if (n == numeroAAdivinar) {
                System.out.println("¡Acertaste!!");
                actualizaRecord();
                break;
            } else {
                // Quitar vida y mostrar pista SIEMPRE
                if (numeroAAdivinar > n) {
                    System.out.println("El número a adivinar es mayor.");
                } else {
                    System.out.println("El número a adivinar es menor.");
                }

                boolean quedanVidas = quitaVida();
                if (quedanVidas) {
                    System.out.println("Vidas restantes: " + getVidasRestantes());
                } else {
                    System.out.println("Sin vidas. Fin de la partida.");
                    System.out.println("El número era: " + numeroAAdivinar);
                    break;
                }
            }
        }
    }
}
