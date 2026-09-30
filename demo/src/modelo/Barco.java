package modelo;
public class Barco extends Veiculo {
    private double comprimento; // em metros

    public Barco(String marca, String modelo, MatriculaBarco matricula, double comprimento) {
        super(marca, modelo, matricula);
        this.comprimento = comprimento;
    }

    public double getComprimento() { return comprimento; }

    public void ancorar() {
        System.out.println(marca + " " + modelo + " ancorou.");
    }

    @Override
    public String getTipo() { return "Barco"; }

    @Override
    public String toString() {
        return super.toString() + " - " + comprimento + "m";
    }
}