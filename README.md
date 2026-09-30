# Garagem de Veículos — Trabalho de Java

Projeto desenvolvido para a disciplina de [nome da disciplina], demonstrando conceitos de Programação Orientada a Objetos (POO) em Java: encapsulamento, herança, polimorfismo, interfaces, composição, validação de dados, tratamento de exceções e dois padrões de software (Singleton e Factory Method).

## Ideia geral do projeto

O programa simula uma garagem com capacidade para 5 veículos. O utilizador pode criar Carros, Motos e Barcos através de um menu interativo no terminal, cada um com a sua matrícula gerada automaticamente, e pode listar ou remover os veículos estacionados.

---

## Estrutura de classes

### `Identificacao` (interface)
Define o contrato que qualquer tipo de matrícula tem de cumprir: o método `getNumero()`. Como `Matricula` (usada por Carros e Motos) e `MatriculaBarco` são muito diferentes uma da outra, a interface permite que a classe `Veiculo` trabalhe com qualquer uma delas sem saber os detalhes de cada tipo. Isto é um exemplo de **polimorfismo**.

### `Matricula` (implementa `Identificacao`)
Representa a matrícula de Carros e Motos, no formato português `AA-00-AA`.
- **Validação no construtor**: usa uma expressão regular (`regex`) para confirmar o formato, e verifica se o ano está entre 1886 (ano do primeiro automóvel do mundo, o Benz Patent-Motorwagen) e 2026. Se os dados forem inválidos, lança `IllegalArgumentException` — o objeto nem chega a ser criado (princípio de "fail-fast").
- **`gerarAutomatica(int anoRegisto)`**: método `static` que gera um número de matrícula aleatório e válido, para o utilizador não ter de o escrever manualmente.
- **`getNumero()`, `getAnoRegisto()`**: getters, permitem ler os dados privados sem os expor diretamente (encapsulamento).
- **`isAntiga()`**: devolve `true` se o ano de registo for anterior a 2000.
- **`getIdade(int anoAtual)`**: calcula há quantos anos a matrícula foi registada.
- **`toString()`**: sobrescreve o método herdado de `Object`, para mostrar a matrícula de forma legível.

### `MatriculaBarco` (implementa `Identificacao`)
Representa a matrícula de um Barco, num formato de exemplo com 5 blocos: `#-AA-#-###-##` (ex: `3-KX-1-045-22`).

> **Nota sobre este formato**: não corresponde ao sistema oficial português de matrículas de embarcações de recreio (que é `NOME - ###### - $PT`, segundo a Administração Marítima/DGRM). Foi usado um formato de exemplo pedido para o exercício.

- **Validação no construtor**: cada um dos 5 blocos é validado individualmente (tamanho e tipo de caracteres).
- **`gerarAutomatica(String nome)`**: gera automaticamente todos os blocos da matrícula (número, letras e restantes dígitos); o utilizador só indica o nome do barco.
- **`getNumero()`**: monta o texto final da matrícula a partir dos blocos guardados, usando `String.format` para garantir sempre o mesmo número de dígitos (ex: `007` em vez de `7`).
- **`toString()`**: mostra o nome do barco seguido da matrícula entre parêntesis.

### `Veiculo` (classe abstrata)
Representa o que é **comum** a qualquer veículo: marca, modelo e matrícula (do tipo `Identificacao`, para aceitar tanto `Matricula` como `MatriculaBarco`).
- É `abstract` porque não faz sentido existir um "Veículo genérico" sozinho — só faz sentido através das suas subclasses.
- **`getTipo()`**: método `abstract`, obriga cada subclasse a dizer que tipo de veículo é ("Carro", "Moto" ou "Barco").
- **`toString()`**: monta a parte comum do texto (tipo, marca, modelo, matrícula), reaproveitada pelas subclasses através de `super.toString()`.

### `Carro extends Veiculo`
- Acrescenta o atributo `importado` (boolean).
- **`buzinar()`**: método de comportamento específico do carro.
- **`getTipo()`**: devolve `"Carro"`.
- **`toString()`**: reaproveita `super.toString()` e acrescenta "Importado" ou "Nacional".

### `Moto extends Veiculo`
- Acrescenta o atributo `cilindrada` (int), com **validação de limites**: tem de estar entre 50cc e 2500cc, senão o construtor lança `IllegalArgumentException`.
- **`empinar()`**: método de comportamento específico da moto.
- **`getTipo()`**: devolve `"Moto"`.

### `Barco extends Veiculo`
- Acrescenta o atributo `comprimento` (double, em metros).
- **`ancorar()`**: método de comportamento específico do barco.
- **`getTipo()`**: devolve `"Barco"`.

### `Garagem` (padrão Singleton)
Guarda a lista de veículos estacionados, com um limite máximo de **5**.
- **Padrão Singleton**: o construtor é `private`, e o único acesso à garagem é através de `Garagem.getInstancia()`, que cria a instância só na primeira vez que é chamada e devolve sempre a mesma depois disso. Isto garante que existe **apenas uma garagem** em todo o programa.
- **`adicionarVeiculo()`**: só adiciona se ainda houver vaga (`size() < 5`); caso contrário, avisa que a garagem está cheia.
- **`removerVeiculo(int indice)`**: remove um veículo pela posição na lista (índice interno, começa em 0).
- **`listarVeiculos()`**: mostra todos os veículos estacionados, numerados de 1 a 5 para o utilizador (por dentro, a lista continua indexada a partir de 0, como é padrão em Java — a soma de 1 acontece só na apresentação).
- **`List<Veiculo>`**: como a lista é do tipo `Veiculo` (a classe-mãe), consegue guardar Carros, Motos e Barcos ao mesmo tempo — outro exemplo de polimorfismo.

### `VeiculoFactory` (padrão Factory Method)
Centraliza a criação de todos os veículos numa única classe, com métodos `criarCarro()`, `criarMoto()` e `criarBarco()`.
- Em vez de o `Main` chamar diretamente `new Carro(...)`, `new Moto(...)`, `new Barco(...)`, chama a Factory. Isto separa "quem pede a criação" de "quem sabe construir o objeto", tornando o código mais fácil de manter — se um dia for preciso mudar como um Carro é construído, só se altera num sítio.

### `Main`
Classe com o menu interativo (ponto de entrada do programa).
- **`lerInteiro(String prompt)` / `lerDouble(String prompt)`**: métodos auxiliares que pedem um número ao utilizador repetidamente até receberem um valor válido, evitando que o programa feche com erro se o utilizador escrever texto em vez de um número.
- **`adicionarCarro()`, `adicionarMoto()`, `adicionarBarco()`**: pedem os dados específicos de cada tipo de veículo, geram a matrícula automaticamente e adicionam à garagem através da Factory.
- **`removerVeiculo()`**: pede ao utilizador o número do veículo (1 a 5, como mostrado na lista) e converte para o índice interno (0 a 4) antes de remover.
- **`switch` + `do...while`**: estrutura do menu, repete até o utilizador escolher sair (opção 0).

---

## Conceitos de POO demonstrados

| Conceito | Onde aparece |
|---|---|
| Encapsulamento | Atributos `private` em todas as classes, acedidos via getters |
| Herança | `Carro`, `Moto`, `Barco` herdam de `Veiculo` |
| Polimorfismo | `List<Veiculo>` guarda tipos diferentes; `Identificacao` aceita `Matricula` ou `MatriculaBarco` |
| Classes abstratas | `Veiculo` |
| Interfaces | `Identificacao` |
| Composição | `Veiculo` "tem uma" `Identificacao` |
| Tratamento de exceções | `try/catch` com `IllegalArgumentException` em várias validações |
| Padrão Singleton | `Garagem` |
| Padrão Factory Method | `VeiculoFactory` |

---

## Funcionalidades

- Criação automática de matrícula para Carros e Motos (formato `AA-00-AA`), validada por ano (1886-2026).
- Criação automática de matrícula para Barcos (formato de 5 blocos), a partir apenas do nome dado pelo utilizador.
- Validação de cilindrada nas Motos (50cc a 2500cc).
- Limite de 5 veículos na garagem, com aviso quando está cheia.
- Leitura de números à prova de erro: se o utilizador escrever texto em vez de um número, o programa pede novamente em vez de fechar.
- Listagem dos veículos numerada de 1 a 5, para ficar mais intuitiva ao utilizador.
- Remoção de veículos pelo número apresentado na lista.

## Como executar

```bash
cd demo/src
javac -encoding UTF-8 *.java
java Main
```

## Estrutura de ficheiros

```
wagner/
 ├── demo/
 │    └── src/
 │         ├── Identificacao.java
 │         ├── Matricula.java
 │         ├── MatriculaBarco.java
 │         ├── Veiculo.java
 │         ├── Carro.java
 │         ├── Moto.java
 │         ├── Barco.java
 │         ├── Garagem.java
 │         ├── VeiculoFactory.java
 │         └── Main.java
 └── README.md
```
