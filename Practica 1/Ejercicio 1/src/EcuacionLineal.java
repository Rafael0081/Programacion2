import java.util.Scanner;

public class EcuacionLineal {
    private double a, b, c, d, e, f;

    public EcuacionLineal(double a, double b, double c, double d, double e, double f) {
        this.a = a; this.b = b; this.c = c; this.d = d; this.e = e; this.f = f;
    }

    public boolean tieneSolucion() {
        return Math.abs(a * d - b * c) > 1e-12;
    }

    public double getX() {
        double denom = a * d - b * c;
        if (Math.abs(denom) < 1e-12) return Double.NaN;
        return (e * d - b * f) / denom;
    }

    public double getY() {
        double denom = a * d - b * c;
        if (Math.abs(denom) < 1e-12) return Double.NaN;
        return (a * f - e * c) / denom;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese a, b, c, d, e, f: ");
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();
        double d = sc.nextDouble();
        double e = sc.nextDouble();
        double f = sc.nextDouble();

        EcuacionLineal eq = new EcuacionLineal(a,b,c,d,e,f);
        if (eq.tieneSolucion()) {
            System.out.printf("x = %.6f, y = %.6f%n", eq.getX(), eq.getY());
        } else {
            System.out.println("La ecuacion no tiene solucion");
        }
        sc.close();
    }
}
