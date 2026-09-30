package modelo;
public class VeiculoFactory {

    public static Carro criarCarro(String marca, String modelo, Matricula matricula, boolean importado) {
        return new Carro(marca, modelo, matricula, importado);
    }

    public static Moto criarMoto(String marca, String modelo, Matricula matricula, int cilindrada) {
        return new Moto(marca, modelo, matricula, cilindrada);
    }

    public static Barco criarBarco(String marca, String modelo, MatriculaBarco matricula, double comprimento) {
        return new Barco(marca, modelo, matricula, comprimento);
    }
}