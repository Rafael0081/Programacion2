import java.util.Scanner;

public class Estadistica {
    private double[] datos;

    public Estadistica(double[] datos) {
        this.datos = datos.clone();
    }

    public double promedio() {
        double suma = 0.0;
        for (double v : datos) suma += v;
        return suma / datos.length;
    }

    public double desviacion() {
        int n = datos.length;
        if (n < 2) return 0.0;
        double mean = promedio();
        double s = 0.0;
        for (double v : datos) s += (v - mean) * (v - mean);
        return Math.sqrt(s / (n - 1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] datos = new double[10];
        System.out.println("Ingrese 10 numeros:");
        for (int i = 0; i < 10; i++) datos[i] = sc.nextDouble();

        Estadistica est = new Estadistica(datos);
        System.out.printf("El promedio es %.5f%n", est.promedio());
        System.out.printf("La desviacion estandard es %.5f%n", est.desviacion());
        sc.close();
    }
}
