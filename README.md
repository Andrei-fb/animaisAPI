# 🐾 API de Animais

API REST em **Spring Boot** para catalogar animais de estimação.  
Feita em **Java 21** com **Spring Boot 3**, **H2 Database** e **Spring Data JPA**.

## 🚀 Como rodar

1. Clone ou extraia o projeto.
2. Abra no IntelliJ IDEA como **Projeto Maven**.
3. Execute a classe `Application.java`.
4. Acesse a API em `http://localhost:8080/animais`.

> O banco H2 é em memória. Para visualizar, vá em `http://localhost:8080/h2-console`  
> JDBC URL: `jdbc:h2:mem:animaisdb` | User: `sa` | Senha: *(em branco)*

---

## 📌 Rotas

### GET `/animais`
Lista todos os animais.

**Exemplo de resposta:**
```json
[
  {
    "id": 1,
    "nome": "Rex",
    "especie": "Cachorro",
    "idade": 5
  }
]
```

### GET `/animais/{id}`
Busca um animal pelo ID.

- **200 OK** se encontrado  
- **404 Not Found** se não existe

### POST `/animais`
Adiciona novo animal.

**Body JSON:**
```json
{
  "nome": "Mimi",
  "especie": "Gato",
  "idade": 3
}
```

- **201 Created** em caso de sucesso  
- **400 Bad Request** se falhar validação

### PUT `/animais/{id}`
Atualiza os dados de um animal.

**Body JSON:**
```json
{
  "nome": "Rex Atualizado",
  "especie": "Cachorro",
  "idade": 6
}
```

- **200 OK** se atualizado  
- **400 Bad Request** se falhar validação  
- **404 Not Found** se ID não existe

### DELETE `/animais/{id}`
Remove um animal.

- **204 No Content** se removido  
- **404 Not Found** se ID não existe

---

## ✅ Validações
- `nome` e `especie` são obrigatórios (`@NotBlank`).
- `idade` deve ser >= 0 (`@Min(0)`).

---

## 🛠️ Tecnologias usadas
- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- Bean Validation (Jakarta Validation)
