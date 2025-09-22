public class Juego {
    protected int numeroDeVidas;
    protected int record;
    protected int vidasRestantes;

    public Juego(int numeroDeVidas) {
        this.numeroDeVidas = numeroDeVidas;
        this.vidasRestantes = numeroDeVidas;
        this.record = 0;
    }

    public void reiniciaPartida() {
        vidasRestantes = numeroDeVidas;
    }

    public void actualizaRecord() {
        if (vidasRestantes > record) {
            record = vidasRestantes;
            System.out.println("(Nuevo record: " + record + ")");
        }
    }

    public boolean quitaVida() {
        vidasRestantes--;
        return vidasRestantes > 0;
    }

    public int getVidasRestantes() {
        return vidasRestantes;
    }
}
