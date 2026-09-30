package modelo;
public class Moto extends Veiculo {
    private static final int CILINDRADA_MINIMA = 50;
    private static final int CILINDRADA_MAXIMA = 2500;

    private int cilindrada;

    public Moto(String marca, String modelo, Matricula matricula, int cilindrada) {
        super(marca, modelo, matricula);
        if (cilindrada < CILINDRADA_MINIMA || cilindrada > CILINDRADA_MAXIMA) {
            throw new IllegalArgumentException(
                "Cilindrada inválida. Tem de estar entre " + CILINDRADA_MINIMA + " e " + CILINDRADA_MAXIMA + "cc.");
        }
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() { return cilindrada; }

    public void empinar() {
        System.out.println(marca + " " + modelo + " empinou a roda da frente!");
    }

    @Override
    public String getTipo() { return "Moto"; }

    @Override
    public String toString() {
        return super.toString() + " - " + cilindrada + "cc";
    }
}