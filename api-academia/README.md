# API Academia

Pequena API REST para gerenciamento de alunos e instrutores.

Como executar

- Para compilar e rodar os testes:

```powershell
cd .\api-academia
.\mvnw.cmd -DskipTests=false test
```

- A aplicação inicia na porta padrão (8080) quando executada com `mvn spring-boot:run`.

OpenAPI / Swagger UI

- Após iniciar a aplicação (perfil default ou dev), a documentação Swagger UI estará disponível em:

  http://localhost:8080/swagger-ui/index.html

Testes de integração

- Os testes de integração estão disponíveis em `src/test/java/.../controller` e usam `MockMvc` com autenticação HTTP Basic (usuário `admin` / senha `password` por padrão em ambientes dev/test).
