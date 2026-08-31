# Desafio Backend Itaú - Resolução 1.1

Este projeto é uma implementação do desafio técnico do Itaú para uma vaga de backend. A API foi desenvolvida utilizando Java e Spring Boot, focando em performance e processamento em memória, conforme solicitado nos requisitos.

> ### Link para o desafio original: [https://github.com/feltex/desafio-itau-backend](https://github.com/feltex/desafio-itau-backend)

---

# Sumário
* [Descrição do projeto](#descrição-do-projeto)
* [Endpoints da API](#endpoints-da-api)
* [Como rodar o projeto](#como-rodar-o-projeto)
* [Como rodar o projeto com Docker](#como-rodar-o-projeto-com-docker)

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
| `GET` | `/unibanco/transacao` | Retorna todas as transações já efetuadas |
| `GET` | `/unibanco/estatistica?periodo=` | Retorna as estatísticas das transações considerando o período em segundos enviado como parâmetro. |
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


# Como rodar o projeto com Docker

Este projeto também possui a opção de ser executado em contâiners, garantindo isolamento e facilidade de execução sem a necessidade de configurar o Java ou Maven localmente. A aplicação ficará disponível na porta 8080.

### Via Docker Compose (Recomendado):

O método mais rápido para subir a aplicação é através do Docker Compose, para isso você precisa:

1. Possuir Docker e Docker Compose instalados em sua máquina;
2. Na raíz do projeto, executo o comando abaixo para construir a imagem e iniciar o contâiner em segundo plano:
```bash
docker compose up 
```

Assim que estes passos forem executados, a API estará pronta para receber requisições em https://localhost:8080


### Via Docker CLI

Caso prefira gerenciar o contâiner manualmente utilizando apenas o Docker CLI, os passos serão:

1. Possuir Docker instalado em sua máquina;
2. Na raíz do projeto, executo o comando abaixo para construir a imagem da aplicação:
```bash
docker build -t desafio-itau-backend .
```
3. Inicie o contâiner mapeando a porta necessária:
```bash
docker run -d -p 8080:8080 --name api-itau desafio-itau-backend	
```
