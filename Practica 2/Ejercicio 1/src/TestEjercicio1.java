class Vector3D {
    private double x, y, z;

    public Vector3D() { this(0,0,0); }
    public Vector3D(double x, double y, double z) {
        this.x = x; this.y = y; this.z = z;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }

    // Operaciones básicas
    public Vector3D suma(Vector3D v) {
        return new Vector3D(x + v.x, y + v.y, z + v.z);
    }
    public Vector3D resta(Vector3D v) {
        return new Vector3D(x - v.x, y - v.y, z - v.z);
    }
    public double magnitud() {
        return Math.sqrt(x*x + y*y + z*z);
    }
    public Vector3D escalar(double r) {
        return new Vector3D(r*x, r*y, r*z);
    }

    public double productoEscalar(Vector3D v) {
        return x*v.x + y*v.y + z*v.z;
    }
    public Vector3D productoVectorial(Vector3D v) {
        return new Vector3D(
                y*v.z - z*v.y,
                z*v.x - x*v.z,
                x*v.y - y*v.x
        );
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}

// Clase con sobrecarga de métodos para álgebra vectorial
class AlgebraVectorial {
    // Perpendicularidad - distintos criterios
    public static boolean esPerpendicular(Vector3D a, Vector3D b) {
        return a.productoEscalar(b) == 0;
    }

    public static boolean esPerpendicular(Vector3D a, Vector3D b, int metodo) {
        switch(metodo) {
            case 1: return a.suma(b).magnitud() == a.resta(b).magnitud();
            case 2: return a.resta(b).magnitud() == b.resta(a).magnitud();
            case 3: return a.productoEscalar(b) == 0;
            case 4: return Math.pow(a.suma(b).magnitud(),2) ==
                    Math.pow(a.magnitud(),2) + Math.pow(b.magnitud(),2);
            default: return false;
        }
    }

    // Paralelismo - distintos criterios
    public static boolean esParalelo(Vector3D a, Vector3D b) {
        return a.productoVectorial(b).magnitud() == 0;
    }
    public static boolean esParalelo(Vector3D a, Vector3D b, double r) {
        return a.getX() == r*b.getX() &&
                a.getY() == r*b.getY() &&
                a.getZ() == r*b.getZ();
    }

    public static Vector3D proyeccion(Vector3D a, Vector3D b) {
        double escalar = a.productoEscalar(b) / Math.pow(b.magnitud(), 2);
        return b.escalar(escalar);
    }

    public static double componente(Vector3D a, Vector3D b) {
        return a.productoEscalar(b) / b.magnitud();
    }
}

class TestEjercicio1 {
    public static void main(String[] args) {
        Vector3D a = new Vector3D(1,0,0);
        Vector3D b = new Vector3D(0,1,0);
        Vector3D c = new Vector3D(2,0,0);

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);

        System.out.println("¿a ⟂ b? " + AlgebraVectorial.esPerpendicular(a,b));
        System.out.println("¿a ∥ c? " + AlgebraVectorial.esParalelo(a,c));
        System.out.println("Proyección de a sobre b = " + AlgebraVectorial.proyeccion(a,b));
        System.out.println("Componente de a en b = " + AlgebraVectorial.componente(a,b));
    }
}
