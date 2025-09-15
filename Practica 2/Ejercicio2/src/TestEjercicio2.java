class Vector3D {
    private double x, y, z;

    // Constructores
    public Vector3D() { this(0,0,0); }
    public Vector3D(double x, double y, double z) {
        this.x = x; this.y = y; this.z = z;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }

    // Suma de vectores
    public Vector3D suma(Vector3D v) {
        return new Vector3D(x + v.x, y + v.y, z + v.z);
    }

    // Multiplicación por escalar
    public Vector3D multiplicarEscalar(double r) {
        return new Vector3D(r*x, r*y, r*z);
    }

    // Longitud del vector
    public double magnitud() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    // Normalización
    public Vector3D normalizar() {
        double m = magnitud();
        if (m == 0) return new Vector3D(0,0,0);
        return new Vector3D(x/m, y/m, z/m);
    }

    // Producto escalar
    public double productoEscalar(Vector3D v) {
        return x*v.x + y*v.y + z*v.z;
    }

    // Producto vectorial
    public Vector3D productoVectorial(Vector3D v) {
        return new Vector3D(
                y*v.z - z*v.y,
                z*v.x - x*v.z,
                x*v.y - y*v.x
        );
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}

public class TestEjercicio2 {
    public static void main(String[] args) {
        Vector3D a = new Vector3D(1,2,3);
        Vector3D b = new Vector3D(4,5,6);

        System.out.println("a = " + a);
        System.out.println("b = " + b);

        System.out.println("a + b = " + a.suma(b));
        System.out.println("2 * a = " + a.multiplicarEscalar(2));
        System.out.println("|a| = " + a.magnitud());
        System.out.println("Normal de a = " + a.normalizar());
        System.out.println("a · b = " + a.productoEscalar(b));
        System.out.println("a × b = " + a.productoVectorial(b));
    }
}
