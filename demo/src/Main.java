public class Main {
    public static void main(String[] args) {
        Matricula m1 = new Matricula("AA-12-BB", 2020);
        Carro carro1 = new Carro("Toyota", "Corolla", m1);

        System.out.println(carro1);
        carro1.acelerar(50);
        carro1.travar(20);
        carro1.buzinar();
        System.out.println("Matrícula antiga? " + m1.isAntiga());
    }
}