import java.util.Random;
import java.util.Scanner;

public class AdivinaNumero extends Juego {
    private int numeroAAdivinar;
    private Scanner sc = new Scanner(System.in);

    public AdivinaNumero(int numeroDeVidas) {
        super(numeroDeVidas);
    }

    public boolean validaNumero(int n) {
        return n >= 0 && n <= 10;
    }

    public void juega() {
        reiniciaPartida();
        numeroAAdivinar = new Random().nextInt(11); // 0..10

        System.out.println("\n--- Juego Adivina Número ---");
        System.out.println("Adivina un número entre 0 y 10.");

        while (true) {
            System.out.print("Ingresa un entero (0-10): ");
            int n;
            try {
                n = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
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
                boolean quedan = quitaVida();
                if (quedan) {
                    if (numeroAAdivinar > n) System.out.println("El número es mayor.");
                    else System.out.println("El número es menor.");
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
