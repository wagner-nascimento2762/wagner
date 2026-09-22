# Carro e Matrícula — Trabalho de Java

Projeto criado para a disciplina de [nome da disciplina], demonstrando conceitos de Programação Orientada a Objetos em Java: encapsulamento, composição, validação de dados e tratamento de exceções.

## Classes

### Matricula
Representa a matrícula de um carro. Contém número e ano de registo, com validação de formato.
- `validarFormato()`: usa uma expressão regular para garantir que o número segue o padrão português AA-00-AA.
- Construtor: rejeita dados inválidos (formato errado ou ano fora do intervalo 1900–2026), lançando `IllegalArgumentException`.
- `getNumero()`, `getAnoRegisto()`: acedem aos dados privados (getters).
- `isAntiga()`: verifica se a matrícula é anterior ao ano 2000.
- `getIdade(int anoAtual)`: calcula há quantos anos a matrícula foi registada.

### Carro
Representa um carro, que "tem uma" Matricula (composição — um Carro contém um objeto Matricula).
- `acelerar(double)`: aumenta a velocidade atual.
- `travar(double)`: diminui a velocidade, nunca abaixo de 0.
- `buzinar()`: imprime um som simples.

### Main
Classe de teste: cria os objetos, chama os métodos, e testa o tratamento de erros com `try/catch` ao tentar criar uma matrícula inválida propositadamente.

## Como executar
1. `cd demo/src`
2. `javac -encoding UTF-8 *.java`
3. `java Main`
