public class AdivinaPar extends AdivinaNumero {

    public AdivinaPar(int numeroDeVidas) {
        super(numeroDeVidas);
    }

    @Override
    public boolean validaNumero(int n) {
        if (n < 0 || n > 10) return false;
        if (n % 2 == 0) return true;
        System.out.println("Error: el número debe ser PAR.");
        return false;
    }
}
