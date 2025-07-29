# Forum Hub Challenge - Back End

![Java](https://img.shields.io/badge/Java-21-007396?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-6DB33F?logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-4.0.0-C71A36?logo=apachemaven&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-10.0-CC0200?logo=flyway&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.3-6DB33F?logo=springsecurity&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-4.5.0-yellow?logo=security&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-1.18.30-BC2F2F?logo=lombok&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-blue)



Este projeto é uma API REST desenvolvida em **Java com Spring Boot** para gerenciar um fórum de discussão. Ele permite que usuários criem tópicos, respondam, atualizem e deletem tópicos, com controle de autenticação via JWT e gerenciamento de permissões com base em perfis de usuário (ROLE_USER e ROLE_ADMIN).

---

## 🚀 Tecnologias utilizadas
- **Java 21**
- **Spring Boot 3**
- **Spring Security (JWT)**
- **Spring Data JPA**
- **MySQL**
- **Maven**
- **Hibernate Validator**
- **Flyway (migrations)**

---

## ⚙️ Configuração do ambiente

### 1️⃣ Clonar o repositório
```bash
git clone https://github.com/wellingtonnovais/forum-hub-challenge-back-end.git
cd forum-hub-challenge-back-end
```

### 2️⃣ Configurar banco de dados
Crie um banco de dados no MySQL:
```sql
CREATE DATABASE forum_hub;
```

### 3️⃣ Configurar variáveis de ambiente
No arquivo `application.properties` ou via variáveis de ambiente, defina:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/forum_hub
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

api.security.token.secret=MINHA_CHAVE_SECRETA
```

### 4️⃣ Rodar migrations
O Flyway executará automaticamente as migrations ao iniciar a aplicação.

### 5️⃣ Executar a aplicação
```bash
mvn spring-boot:run
```

---

## 🔑 Autenticação
A API utiliza **JWT (JSON Web Token)** para autenticação.

Perfis disponíveis:
- **ROLE_USER** → pode criar tópicos, atualizar apenas os seus próprios, e responder tópicos de outros usuários.
- **ROLE_ADMIN** → pode criar, atualizar e deletar qualquer tópico.

---

## 📂 Estrutura do banco de dados
**usuarios**
| id  | nome           | email             | senha (BCrypt) |
|-----|--------------- |-----------------  |---------------|

**perfis**
| id  | nome          |
|-----|---------------|
| 1   | ROLE_USER     |
| 2   | ROLE_ADMIN    |

**topicos**
| id  | titulo        | mensagem         | status       | autor_id | curso_id |

**respostas**
| id  | mensagem      | data_resposta    | autor_id    | topico_id |

**atualizacoes**
| id  | titulo        | mensagem         | status      | data      | editor_id | topico_id |

---

## 📌 Endpoints da API

### 🔐 Autenticação
**POST /login**
```json
{
  "email": "pedroh@hotmail.com",
  "senha": "1234567890"
}
```

---

### 📝 Registrar tópico
**POST /topicos/registrar**
```json
{
  "titulo": "Erro ao declarar constante no Python",
  "mensagem": "Estou tentando declarar uma constante em Python usando letras maiúsculas, mas ainda consigo alterar o valor da variável. Existe alguma forma de realmente impedir a modificação da constante?",
  "nomeCurso": "PYTHON_DO_ZERO"
}

```

---

### 📖 Listar todos os tópicos
**GET /topicos/listar**

---

### 🔍 Listar tópico por ID
**GET /topicos/listar/{id}**

Exemplo:  
`http://localhost:8080/topicos/listar/5`

---

### 💬 Responder tópico
**POST /topicos/{id}/responder**
```json
{
  "mensagem": "Em Python não existe constante real, mas você pode usar convenções como letras maiúsculas e ferramentas como linters para evitar mudanças."
}
```

---

### ✏ Atualizar tópico
**PUT /topicos/atualizar/{id}**
```json
{
  "titulo": "Problema resolvido",
  "mensagem": "Entendi que em Python não há constantes reais, então vou seguir a convenção de usar letras maiúsculas. Obrigado pela ajuda!",
  "status": "SOLUCIONADO"
}
```

---

### ❌ Deletar tópico
**DELETE /topicos/deletar/{id}**

---

## 🧑‍💻 Desenvolvedor

Feito com 💙 e dedicação por **Wellington Novais**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Wellington%20Novais-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/wellington-novais-dev/)
[![GitHub](https://img.shields.io/badge/GitHub-wellingtonnovais-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/wellingtonnovais)


## 📜 Licença
Este projeto está sob a licença **MIT**.
