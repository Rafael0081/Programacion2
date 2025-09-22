public class Aplicacion2 {
    public static void main(String[] args) {
        AdivinaPar par = new AdivinaPar(3);
        AdivinaImpar impar = new AdivinaImpar(3);

        par.juega();
        System.out.println("\n----------------------\n");
        impar.juega();
    }
}
