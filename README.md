

# 🏋️‍♀️ API RESTful de Academia com Spring Boot e Java

Este projeto consiste em uma API RESTful simples desenvolvida com Spring Boot e Java para gerenciar informações de uma academia. A API permite realizar operações CRUD (Criar, Ler, Atualizar, Deletar) para recursos como **Alunos** e **Instrutores**.

## ✨ Funcionalidades

* **Alunos:**
    * Criação, listagem, busca por ID, atualização e remoção de registros de alunos.
* **Instrutores:**
    * Criação, listagem, busca por ID, atualização e remoção de registros de instrutores.
* **Banco de Dados H2 em Memória:**
    * Utilização de um banco de dados H2 em memória para facilitar o desenvolvimento e testes, com console web para visualização.
* **Validação de Dados:**
    * Validação básica de entrada de dados para garantir a integridade das informações.
* **Segurança (Spring Security):**
    * Autenticação HTTP Basic para acesso aos endpoints da API, com usuário e senha configuráveis.

## 🚀 Tecnologias Utilizadas

* **Java 21+**
* **Spring Boot 3.3.0+**
* **Spring Web**: Criação de endpoints RESTful.
* **Spring Data JPA**: Abstração para persistência de dados.
* **H2 Database**: Banco de dados relacional em memória.
* **Lombok**: Redução de boilerplate code (getters, setters, construtores, etc.).
* **Spring Boot Starter Validation**: Para validação de objetos.
* **Spring Security**: Segurança da aplicação.
* **Maven**: Gerenciador de dependências e build.

## 🛠️ Como Executar o Projeto

### Pré-requisitos

Certifique-se de ter o seguinte instalado em sua máquina:

* **JDK 21 ou superior**
* **Maven 3.6 ou superior**

### Passos para Executar

1. **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/api-academia.git](https://github.com/seu-usuario/api-academia.git)
    cd api-academia
    ```
    (Substitua `seu-usuario` e `api-academia` pelo seu repositório real)

2. **Compile o projeto:**
    ```bash
    mvn clean install
    ```

3. **Execute a aplicação Spring Boot:**
    ```bash
    mvn spring-boot:run
    ```

    A API estará rodando em `http://localhost:8080`.

## 🌐 Endpoints da API

A API requer autenticação HTTP Basic. As credenciais padrão são:
* **Usuário**: `admin`
* **Senha**: `password`

### Alunos (`/api/alunos`)

| Método | Endpoint | Descrição | Corpo da Requisição (Exemplo) |
| :----- | :---------------------- | :----------------------------- | :--------------------------------------------------------------- |
| `GET` | `/api/alunos` | Lista todos os alunos. | - |
| `GET` | `/api/alunos/{id}` | Busca um aluno por ID. | - |
| `POST` | `/api/alunos` | Cria um novo aluno. | `{ "nome": "João Silva", "cpf": "123.456.789-00", "email": "joao@example.com", "telefone": "9999-8888" }` |
| `PUT` | `/api/alunos/{id}` | Atualiza um aluno existente. | `{ "nome": "João Silva", "cpf": "123.456.789-00", "email": "joao.novo@example.com", "telefone": "9999-8888" }` |
| `DELETE`| `/api/alunos/{id}` | Deleta um aluno por ID. | - |

### Instrutores (`/api/instrutores`)

| Método | Endpoint | Descrição | Corpo da Requisição (Exemplo) |
| :----- | :-------------------------- | :----------------------------- | :---------------------------------------------------------------------- |
| `GET` | `/api/instrutores` | Lista todos os instrutores. | - |
| `GET` | `/api/instrutores/{id}` | Busca um instrutor por ID. | - |
| `POST` | `/api/instrutores` | Cria um novo instrutor. | `{ "nome": "Maria Souza", "cpf": "000.987.654-32", "especialidade": "Musculação", "telefone": "7777-6666" }` |
| `PUT` | `/api/instrutores/{id}` | Atualiza um instrutor existente.| `{ "nome": "Maria Souza", "cpf": "000.987.654-32", "especialidade": "Spinning", "telefone": "7777-6666" }` |
| `DELETE`| `/api/instrutores/{id}` | Deleta um instrutor por ID. | - |

## 🧪 Console H2

Durante o desenvolvimento, você pode acessar o console do banco de dados H2 para inspecionar os dados:

* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:academiadb`
* **User Name:** `sa`
* **Password:** (deixe em branco)

## 🤝 Contribuição

Sinta-se à vontade para sugerir melhorias, abrir issues ou enviar pull requests.

## 📄 Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---
