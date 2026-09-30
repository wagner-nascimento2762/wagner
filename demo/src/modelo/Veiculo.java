package modelo;
public abstract class Veiculo {
    protected String marca;
    protected String modelo;
    protected Identificacao matricula;

    public Veiculo(String marca, String modelo, Identificacao matricula) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
    }

    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public Identificacao getMatricula() { return matricula; }

    // Cada subclasse tem de dizer que tipo de veículo é
    public abstract String getTipo();

    @Override
    public String toString() {
        return "[" + getTipo() + "] " + marca + " " + modelo + " - Matrícula: " + matricula;
    }
}