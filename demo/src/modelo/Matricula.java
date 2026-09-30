package modelo;
import java.util.Random;

public class Matricula implements Identificacao {
    private String numero;
    private int anoRegisto;

    private static final Random random = new Random();

    public Matricula(String numero, int anoRegisto) {
        if (!validarFormato(numero)) {
            throw new IllegalArgumentException("Formato de matrícula inválido. Use AA-00-AA");
        }
        if (anoRegisto < 1886 || anoRegisto > 2026) {
            throw new IllegalArgumentException("Ano de registo inválido. O primeiro automóvel data de 1886.");
        }
        this.numero = numero.toUpperCase();
        this.anoRegisto = anoRegisto;
    }

    // Gera automaticamente uma matrícula válida, com o ano indicado
    public static Matricula gerarAutomatica(int anoRegisto) {
        String numeroGerado = gerarNumeroAleatorio();
        return new Matricula(numeroGerado, anoRegisto);
    }

    private static String gerarNumeroAleatorio() {
        String letras1 = gerarDuasLetras();
        String numeros = String.format("%02d", random.nextInt(100)); // 00-99
        String letras2 = gerarDuasLetras();
        return letras1 + "-" + numeros + "-" + letras2;
    }

    private static String gerarDuasLetras() {
        char c1 = (char) ('A' + random.nextInt(26));
        char c2 = (char) ('A' + random.nextInt(26));
        return "" + c1 + c2;
    }

    private boolean validarFormato(String numero) {
        return numero != null && numero.matches("[A-Za-z]{2}-\\d{2}-[A-Za-z]{2}");
    }

    @Override
    public String getNumero() {
        return numero;
    }

    public int getAnoRegisto() {
        return anoRegisto;
    }

    public boolean isAntiga() {
        return anoRegisto < 2000;
    }

    public int getIdade(int anoAtual) {
        return anoAtual - anoRegisto;
    }

    @Override
    public String toString() {
        return numero + " (registada em " + anoRegisto + ")";
    }
}