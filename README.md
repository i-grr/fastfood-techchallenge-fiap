# FastFood - FIAP Tech Challenge #01

## Escopo do Projeto

O projeto FastFood é uma aplicação de gerenciamento de pedidos e produtos para um serviço de fast food. A aplicação é desenvolvida em Java utilizando o framework Spring, e inclui funcionalidades para cadastro de clientes, produtos, e realização de pedidos. A arquitetura do projeto segue o padrão Hexagonal, garantindo uma separação clara entre a lógica de negócios e as interfaces de entrada e saída.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação utilizada para desenvolver a aplicação.
- **Spring**: Framework utilizado para criar a aplicação, incluindo Spring Boot para configuração simplificada e Spring Data JPA para persistência de dados.
- **Postgres**: Banco de dados relacional utilizado para armazenar os dados da aplicação.
- **Flyway**: Ferramenta para migração e versionamento de banco de dados, utilizada para gerenciar e aplicar mudanças no esquema do banco de dados de forma controlada.
- **Docker**: Plataforma utilizada para criar, gerenciar e executar contêineres.
- **Docker Compose**: Ferramenta para definir e gerenciar multi-contêineres Docker.


## Estrutura do Projeto

A estrutura do projeto segue a arquitetura Hexagonal, organizada em três camadas principais:

- **Domain**: Contém a lógica de negócios e as entidades do domínio.
- **Adapter**: Contém os adaptadores de entrada e saída, incluindo controladores e repositórios.
- **Config**: Contém as configurações da aplicação, incluindo a configuração do Swagger.

## Rodando o Projeto

Para rodar o projeto, siga os passos abaixo:

### 1. Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

- Docker
- Git
- JDK 17 ou superior

### 2. Clonar o Repositório

Clone o repositório do projeto:

```sh
git clone <https://github.com/i-grr/fastfood-techchallenge-fiap.git
cd fastfood
```

### 3. Executar o Script de Setup

O projeto inclui um script de setup (`setup.sh`) que automatiza o processo de construção e execução do projeto. O script realiza as seguintes operações:

- Para e remove os contêineres Docker, juntamente com seus volumes.
- Executa a construção do projeto Maven.
- Inicia os contêineres Docker em modo destacável e reconstrói as imagens se necessário.

Para executar o script, siga os passos abaixo:

#### macOS e Linux

1. **Tornar o Script Executável**:

    ```sh
    chmod +x setup.sh
    ```

2. **Executar o Script**:

    ```sh
    ./setup.sh
    ```

#### Windows

1. **Executar o Script**:

   No PowerShell ou Git Bash:

    ```sh
    ./setup.sh
    ```

### 4. Acessar o Swagger

Após iniciar a aplicação, você pode acessar a documentação gerada pelo Swagger na seguinte URL:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Endpoints da API

A documentação completa dos endpoints da API pode ser acessada através do Swagger UI. Os principais endpoints incluem:

### Clientes:
- **POST /api/v1/customers:** Cadastro de clientes.
- **GET /api/v1/customers?cpf={cpf}:** Busca de cliente por CPF.

### Produtos:
- **POST /api/v1/products:** Cadastro de produtos.
- **PUT /api/v1/products:** Atualização de produtos.
- **DELETE /api/v1/products/{id}:** Exclusão de produtos.
- **GET /api/v1/products?category={category}:** Listagem de produtos por categoria.

### Pedidos:
- **POST /api/v1/checkouts:** Realização de pedidos (checkout).
- **GET /api/v1/orders:** Consulta de pedidos.

## Configuração do Swagger

A configuração do Swagger está localizada na classe `SwaggerConfig` no pacote `config`. A documentação gerada pelo Swagger pode ser acessada em:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Banco de Dados

O projeto utiliza o PostgreSQL como banco de dados. A configuração do banco de dados é gerenciada pelo Docker Compose, garantindo que o banco de dados esteja configurado e em execução junto com a aplicação.

## Considerações Finais

Este documento fornece uma visão geral do projeto FastFood, incluindo seu escopo, tecnologias utilizadas, estrutura do projeto e instruções para rodar a aplicação. Para mais detalhes sobre os endpoints da API, consulte a documentação Swagger.

Se tiver qualquer dúvida ou encontrar algum problema, sinta-se à vontade para abrir uma issue no repositório ou entrar em contato comigo mesmo, Igor Pereira.


