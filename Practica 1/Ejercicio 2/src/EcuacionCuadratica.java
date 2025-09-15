import java.util.Scanner;

public class EcuacionCuadratica {
    private double a, b, c;

    public EcuacionCuadratica(double a, double b, double c) {
        this.a = a; this.b = b; this.c = c;
    }

    public double getDiscriminante() {
        return b * b - 4 * a * c;
    }

    public double getRaiz1() {
        double d = getDiscriminante();
        if (d < 0 || Math.abs(a) < 1e-12) return 0;
        return (-b + Math.sqrt(d)) / (2 * a);
    }

    public double getRaiz2() {
        double d = getDiscriminante();
        if (d < 0 || Math.abs(a) < 1e-12) return 0;
        return (-b - Math.sqrt(d)) / (2 * a);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese a, b, c: ");
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();

        EcuacionCuadratica eq = new EcuacionCuadratica(a,b,c);
        double d = eq.getDiscriminante();

        if (d > 0) {
            System.out.printf("La ecuacion tiene dos raices %.6f y %.6f%n", eq.getRaiz1(), eq.getRaiz2());
        } else if (Math.abs(d) < 1e-12) {
            // única raíz
            double raiz = -b / (2 * a);
            System.out.printf("La ecuacion tiene una raiz %.6f%n", raiz);
        } else {
            System.out.println("La ecuacion no tiene raices reales");
        }
        sc.close();
    }
}
