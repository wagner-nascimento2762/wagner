package modelo;
public class Carro extends Veiculo {
    private boolean importado;

    public Carro(String marca, String modelo, Matricula matricula, boolean importado) {
        super(marca, modelo, matricula);
        this.importado = importado;
    }

    public boolean isImportado() { return importado; }

    public void buzinar() {
        System.out.println(marca + " " + modelo + ": Biiip!");
    }

    @Override
    public String getTipo() { return "Carro"; }

    @Override
    public String toString() {
        String origem = importado ? "Importado" : "Nacional";
        return super.toString() + " - " + origem;
    }
}