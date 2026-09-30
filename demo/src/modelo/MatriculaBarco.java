package modelo;
public class MatriculaBarco implements Identificacao {
    private static final java.util.Random random = new java.util.Random();

    private String nome;
    private int bloco1;      // 1 dígito
    private String letras;   // 2 letras aleatórias A-Z
    private int bloco3;      // 1 dígito
    private int bloco4;      // 3 dígitos
    private int bloco5;      // 2 dígitos

    public MatriculaBarco(String nome, int bloco1, String letras, int bloco3, int bloco4, int bloco5) {
        if (nome == null || !nome.trim().matches("[A-Za-z0-9 ]+")) {
            throw new IllegalArgumentException(
                "Nome inválido. Use apenas letras (sem acentos), números e espaços.");
        }
        if (bloco1 < 0 || bloco1 > 9) {
            throw new IllegalArgumentException("O 1º número tem de ter 1 dígito (0-9).");
        }
        if (letras == null || !letras.matches("[A-Z]{2}")) {
            throw new IllegalArgumentException("O bloco de letras tem de ter exatamente 2 letras maiúsculas (A-Z).");
        }
        if (bloco3 < 0 || bloco3 > 9) {
            throw new IllegalArgumentException("O 3º número tem de ter 1 dígito (0-9).");
        }
        if (bloco4 < 0 || bloco4 > 999) {
            throw new IllegalArgumentException("O 4º número tem de ter 3 dígitos (000-999).");
        }
        if (bloco5 < 0 || bloco5 > 99) {
            throw new IllegalArgumentException("O 5º número tem de ter 2 dígitos (00-99).");
        }

        this.nome = nome.trim().toUpperCase();
        this.bloco1 = bloco1;
        this.letras = letras;
        this.bloco3 = bloco3;
        this.bloco4 = bloco4;
        this.bloco5 = bloco5;
    }

    // Gera automaticamente todos os blocos da matrícula; o utilizador só indica o nome do barco.
    public static MatriculaBarco gerarAutomatica(String nome) {
        int b1 = random.nextInt(10);          // 0-9
        String letras = gerarDuasLetras();    // AA-ZZ
        int b3 = random.nextInt(10);          // 0-9
        int b4 = random.nextInt(1000);        // 0-999
        int b5 = random.nextInt(100);         // 0-99
        return new MatriculaBarco(nome, b1, letras, b3, b4, b5);
    }

    private static String gerarDuasLetras() {
        char c1 = (char) ('A' + random.nextInt(26));
        char c2 = (char) ('A' + random.nextInt(26));
        return "" + c1 + c2;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String getNumero() {
        return bloco1 + "-" + letras + "-" + bloco3 + "-"
                + String.format("%03d", bloco4) + "-" + String.format("%02d", bloco5);
    }

    @Override
    public String toString() {
        return nome + " (" + getNumero() + ")";
    }
}