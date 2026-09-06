[for english version, click here](#english-version)

# Desafio Backend Itaú - Resolução 2.0

Este projeto é uma implementação do desafio técnico do Itaú. A API foi desenvolvida utilizando Java e Spring Boot, focando em performance e processamento em memória, contando também com uma **interface Front-end** interativa para facilitar o uso.

> ### Link para o desafio original: https://github.com/feltex/desafio-itau-backend

---

# Sumário
* [Descrição do projeto](#descrição-do-projeto)
* [Endpoints da API](#endpoints-da-api)
* [Como rodar o projeto](#como-rodar-o-projeto)
* [Como rodar o projeto com Docker](#como-rodar-o-projeto-com-docker)

---

# Descrição do projeto

A finalidade desta API é receber, apagar e retornar transações financeiras, bem como fornecer estatísticas baseadas nos valores transacionados nos últimos 60 segundos ou em um período estipulado. Através da interface web integrada, o usuário pode inserir transações e visualizar as estatísticas diretamente pelo navegador.

### Principais características:
- **Armazenamento em Memória:** Não utiliza bancos de dados ou caches externos, garantindo baixa latência no processamento.
- **Validação Rigorosa:** 
  - Transações com valores negativos são rejeitadas (HTTP 422).
  - Transações com datas futuras são rejeitadas (HTTP 422).
  - Apenas transações enviadas com JSON válido são aceitas.
- **Cálculo de Estatísticas:** Processa em tempo real a soma, média, valor mínimo, valor máximo e a contagem das transações que ocorreram no último minuto.
 - **Tipos de dados:** Os dados enviados possuem tipos específicos, sendo `valor` um número de ponto flutuante e `dataHora` um valor de data formatado no padrão ISO-8601

# Endpoints da API

Todos os endpoints estão sob o prefixo `/unibanco`.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/unibanco/transacao` | Recebe uma transação com `valor` e `dataHora`. |
| `GET` | `/unibanco/estatistica` | Retorna as estatísticas das transações dos últimos 60 segundos. |
| `GET` | `/unibanco/estatistica?periodo=` | Retorna as estatísticas das transações considerando o período em segundos enviado como parâmetro. |
| `GET` | `/unibanco/transacao` | Retorna todas as transações já efetuadas. |
| `DELETE` | `/unibanco/transacao` | Remove todas as transações armazenadas na memória. |

### Exemplo de JSON para POST:

```
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
   mvn clean install
4. Inicie a aplicação:
   mvn spring-boot:run
5. A aplicação e o front-end estarão disponíveis em http://localhost:8080. Você pode abrir este link no seu navegador para utilizar o sistema.


# Como rodar o projeto com Docker

Este projeto também possui a opção de ser executado em contêineres, garantindo isolamento e facilidade de execução sem a necessidade de configurar o Java ou Maven localmente. A aplicação completa (API + Frontend) ficará disponível na porta 8080.

### Via Docker Compose (Recomendado):

O método mais rápido para subir a aplicação é através do Docker Compose. Para isso você precisa:

1. Possuir Docker e Docker Compose instalados em sua máquina;
2. Na raiz do projeto, execute o comando abaixo para construir a imagem e iniciar o contêiner em segundo plano:
docker compose up -d

Assim que estes passos forem executados, você poderá **abrir a interface no seu navegador** acessando: http://localhost:8080


### Via Docker CLI

Caso prefira gerenciar o contêiner manualmente utilizando apenas o Docker CLI, os passos serão:

1. Possuir Docker instalado em sua máquina;
2. Na raiz do projeto, execute o comando abaixo para construir a imagem da aplicação:
docker build -t desafio-itau-backend .
3. Inicie o contêiner mapeando a porta necessária:
docker run -d -p 8080:8080 --name api-itau desafio-itau-backend	

---

<a id="english-version"></a>
# English Version

# Itaú Backend Challenge - Resolution 2.0

This project is an implementation of Itaú's technical challenge. The API was developed using Java and Spring Boot, focusing on performance and in-memory processing, including an interactive **Front-end interface** for easier usability.

> ### Original challenge link: https://github.com/feltex/desafio-itau-backend

---

# Table of Contents
* [Project Description](#project-description)
* [API Endpoints](#api-endpoints)
* [How to run the project](#how-to-run-the-project)
* [How to run the project with Docker](#how-to-run-the-project-with-docker)

---

# Project Description

The purpose of this API is to receive, delete and return financial transactions, also provinding statistics based on the transacted values in the last 60 seconds or in a specified time. Through the integrated web interface, the user can insert transactions and view statistics directly in the browser.

### Main features:
- **In-Memory Storage:** Does not use external databases or caches, ensuring low latency in processing.
- **Strict Validation:** 
  - Transactions with negative values are rejected (HTTP 422).
  - Transactions with future dates are rejected (HTTP 422).
  - Only transactions sent with valid JSON are accepted.
- **Statistics Calculation:** Processes in real-time the sum, average, minimum value, maximum value, and the count of transactions that occurred in the last minute.
- **Data types:** The send data has two specific types, being `valor` a float number and `dataHora` a datetime value in the format ISO-8601

# API Endpoints

All endpoints are under the `/unibanco` prefix.

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/unibanco/transacao` | Receives a transaction with `valor` (value) and `dataHora` (datetime). |
| `GET` | `/unibanco/estatistica` | Returns transaction statistics for the last 60 seconds. |
| `GET` | `/unibanco/estatistica?periodo=` | Returns transaction statistics considering the period in seconds sent as a parameter. |
| `GET` | `/unibanco/transacao` | Returns all executed transactions. |
| `DELETE` | `/unibanco/transacao` | Removes all transactions stored in memory. |

### JSON Example for POST:
```
{
    "valor": 123.45,
    "dataHora": "2020-08-07T12:34:56.789-03:00"
}
```
# How to run the project

### Prerequisites:
- Java 17 or higher
- Maven 3.x

### Execution steps:
1. Clone the repository.
2. Navigate to the project's root folder.
3. Run the command to download dependencies and compile the project:
   mvn clean install
4. Start the application:
   mvn spring-boot:run
5. The application and front-end will be available at http://localhost:8080. You can open this link in your web browser to use the system.


# How to run the project with Docker

This project also has the option to run in containers, ensuring isolation and ease of execution without the need to configure Java or Maven locally. The complete application (API + Frontend) will be available on port 8080.

### Via Docker Compose (Recommended):

The fastest way to spin up the application is through Docker Compose. To do this, you need to:

1. Have Docker and Docker Compose installed on your machine;
2. In the project root, run the command below to build the image and start the container in the background:
docker compose up -d

Once these steps are executed, you can **open the interface in your browser** by accessing: http://localhost:8080


### Via Docker CLI

If you prefer to manage the container manually using only the Docker CLI, the steps are:

1. Have Docker installed on your machine;
2. In the project root, run the command below to build the application image:
docker build -t desafio-itau-backend .
3. Start the container by mapping the required port:
docker run -d -p 8080:8080 --name api-itau desafio-itau-backend	