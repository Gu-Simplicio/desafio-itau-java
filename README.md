# Desafio Backend Itaú - Unibanco 1.0

Este projeto é uma implementação do desafio técnico do Itaú para uma vaga de backend. A API foi desenvolvida utilizando Java e Spring Boot, focando em performance e processamento em memória, conforme solicitado nos requisitos.

> ### Link para o desafio original: [https://github.com/feltex/desafio-itau-backend](https://github.com/feltex/desafio-itau-backend)

---

# Sumário
* [Descrição do projeto](#descrição-do-projeto)
* [Endpoints da API](#endpoints-da-api)
* [Como rodar o projeto](#como-rodar-o-projeto)

---

# Descrição do projeto

A finalidade desta API é receber transações financeiras e fornecer estatísticas baseadas nos valores transacionados nos últimos 60 segundos. 

### Principais características:
- **Armazenamento em Memória:** Não utiliza bancos de dados ou caches externos, garantindo baixa latência no processamento.
- **Validação Rigorosa:** 
  - Transações com valores negativos são rejeitadas (HTTP 422).
  - Transações com datas futuras são rejeitadas (HTTP 422).
  - Apenas transações enviadas com JSON válido são aceitas.
- **Cálculo de Estatísticas:** Processa em tempo real a soma, média, valor mínimo, valor máximo e a contagem das transações que ocorreram no último minuto.

# Endpoints da API

Todos os endpoints estão sob o prefixo `/unibanco`.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/unibanco/transacao` | Recebe uma transação com `valor` e `dataHora`. |
| `GET` | `/unibanco/estatistica` | Retorna as estatísticas das transações dos últimos 60 segundos. |
| `DELETE` | `/unibanco/transacao` | Remove todas as transações armazenadas na memória. |

### Exemplo de JSON para POST:
```json
{
    "valor": 123.45,
    "dataHora": "2020-08-07T12:34:56.789-03:00"
}
```

# Como rodar o projeto

### Pré-requisitos:
- Java 17 ou superior
- Maven 3.x

### Passos para execução:
1. Clone o repositório.
2. Navegue até a pasta raiz do projeto.
3. Execute o comando para baixar as dependências e compilar o projeto:
   ```bash
   mvn clean install
   ```
4. Inicie a aplicação:
   ```bash
   mvn spring-boot:run
   ```
5. A API estará disponível em `http://localhost:8080`.
