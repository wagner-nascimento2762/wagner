package modelo;
import java.util.ArrayList;
import java.util.List;

public class Garagem {
    private static final int CAPACIDADE_MAXIMA = 5;
    private static Garagem instancia; // única instância da classe

    private List<Veiculo> veiculos;

    // Construtor privado: ninguém de fora consegue fazer "new Garagem()"
    private Garagem() {
        this.veiculos = new ArrayList<>();
    }

    // Ponto único de acesso à instância
    public static Garagem getInstancia() {
        if (instancia == null) {
            instancia = new Garagem();
        }
        return instancia;
    }

    public boolean adicionarVeiculo(Veiculo veiculo) {
        if (veiculos.size() >= CAPACIDADE_MAXIMA) {
            System.out.println("Garagem cheia! Não é possível estacionar mais veículos.");
            return false;
        }
        veiculos.add(veiculo);
        System.out.println(veiculo.getTipo() + " estacionado com sucesso. Matrícula: " + veiculo.getMatricula());
        return true;
    }

    public boolean removerVeiculo(int indice) {
        if (indice < 0 || indice >= veiculos.size()) {
            System.out.println("Índice inválido.");
            return false;
        }
        Veiculo removido = veiculos.remove(indice);
        System.out.println(removido.getTipo() + " saiu da garagem.");
        return true;
    }

    public void listarVeiculos() {
        if (veiculos.isEmpty()) {
            System.out.println("A garagem está vazia.");
            return;
        }
        System.out.println("\n=== Veículos na garagem (" + veiculos.size() + "/" + CAPACIDADE_MAXIMA + ") ===");
        for (int i = 0; i < veiculos.size(); i++) {
            System.out.println(i + " - " + veiculos.get(i));
        }
    }

    public int getVagasDisponiveis() {
        return CAPACIDADE_MAXIMA - veiculos.size();
    }
}