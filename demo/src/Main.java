import java.util.Scanner;

import modelo.Barco;
import modelo.Carro;
import modelo.Garagem;
import modelo.Matricula;
import modelo.MatriculaBarco;
import modelo.Moto;
import modelo.VeiculoFactory;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Garagem garagem = Garagem.getInstancia(); // Singleton

    public static void main(String[] args) {
        int opcao;
        do {
            mostrarMenu();
            opcao = lerInteiro("Escolhe uma opção: ");

            switch (opcao) {
                case 1:
                    adicionarCarro();
                    break;
                case 2:
                    adicionarMoto();
                    break;
                case 3:
                    adicionarBarco();
                    break;
                case 4:
                    garagem.listarVeiculos();
                    break;
                case 5:
                    removerVeiculo();
                    break;
                case 0:
                    System.out.println("A sair...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    static void mostrarMenu() {
        System.out.println("\n=== Garagem (" + garagem.getVagasDisponiveis() + " vagas livres) ===");
        System.out.println("1 - Adicionar Carro");
        System.out.println("2 - Adicionar Moto");
        System.out.println("3 - Adicionar Barco");
        System.out.println("4 - Listar veículos");
        System.out.println("5 - Remover veículo");
        System.out.println("0 - Sair");
    }

    static int lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt);
            String texto = scanner.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Escreve apenas números inteiros (ex: 5).");
            }
        }
    }

    static double lerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String texto = scanner.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(texto);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Escreve apenas números (ex: 7.5).");
            }
        }
    }

    static Matricula gerarMatriculaComAno() {
        Matricula matricula = null;
        while (matricula == null) {
            int ano = lerInteiro("Ano (1886-2026): ");
            try {
                matricula = Matricula.gerarAutomatica(ano);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return matricula;
    }

    static MatriculaBarco criarMatriculaBarco() {
        MatriculaBarco matricula = null;
        while (matricula == null) {
            System.out.print("Nome do barco: ");
            String nome = scanner.nextLine();

            try {
                matricula = MatriculaBarco.gerarAutomatica(nome);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return matricula;
    }

    static void adicionarCarro() {
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Importado? (sim/nao): ");
        boolean importado = scanner.nextLine().trim().equalsIgnoreCase("sim");
        Matricula matricula = gerarMatriculaComAno();

        Carro carro = VeiculoFactory.criarCarro(marca, modelo, matricula, importado);
        garagem.adicionarVeiculo(carro);
    }

    static void adicionarMoto() {
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        Moto moto = null;
        while (moto == null) {
            int cilindrada = lerInteiro("Cilindrada (50-2500cc): ");
            Matricula matricula = gerarMatriculaComAno();

            try {
                moto = VeiculoFactory.criarMoto(marca, modelo, matricula, cilindrada);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        garagem.adicionarVeiculo(moto);
    }

    static void adicionarBarco() {
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        double comprimento = lerDouble("Comprimento (metros): ");
        MatriculaBarco matricula = criarMatriculaBarco();

        Barco barco = VeiculoFactory.criarBarco(marca, modelo, matricula, comprimento);
        garagem.adicionarVeiculo(barco);
    }

    static void removerVeiculo() {
        garagem.listarVeiculos();
        int numero = lerInteiro("Número do veículo a remover: ");
        garagem.removerVeiculo(numero - 1); // o utilizador vê 1-5, a lista usa 0-4 por dentro
    }
}