# TripCritic

Aplicação REST em Spring Boot para cadastro de usuários, pontos turísticos, marcação de visitas e avaliações.

## Sumário
- Requisitos
- Rodando o projeto
  - Opção 1: Docker Compose (PostgreSQL)
  - Opção 2: Banco em memória (H2)
- Endpoints principais
- Validações das requisições (Bean Validation)
- Convenções e boas práticas aplicadas
- Problemas comuns

## Requisitos
- Java 22 (ou toolchain Maven configurada para 22)
- Maven 3.9+
- Docker (opcional, para rodar Postgres via Compose)

## Rodando o projeto

### Opção 1: Usando Docker Compose (PostgreSQL)
1. Suba o PostgreSQL com Docker Compose:
   - Pré‑requisito: Docker instalado e em execução.
   - No diretório raiz do projeto, execute:
     - Linux/macOS: `docker compose up -d`
     - Windows (PowerShell): `docker compose up -d`
   - Isso criará um banco `tripcritic` disponível em `localhost:5432` com usuário `myuser` e senha `secret`.
2. Verifique `src/main/resources/application.properties` (por padrão já está apontando para esse Postgres):
   - `spring.datasource.url=jdbc:postgresql://localhost:5432/tripcritic`
   - `spring.datasource.username=myuser`
   - `spring.datasource.password=secret`
3. Rode a aplicação:
   - `./mvnw spring-boot:run` (Linux/macOS)
   - `mvnw.cmd spring-boot:run` (Windows)
4. API disponível em: `http://localhost:8080`

### Opção 2: Usando H2 (memória)
Se você preferir não usar Docker/Postgres para testes locais rápidos:
1. Altere temporariamente o `application.properties` para H2. Exemplo:
   - `spring.datasource.driver-class-name=org.h2.Driver`
   - `spring.datasource.url=jdbc:h2:mem:tripcritic;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
   - `spring.datasource.username=sa`
   - `spring.datasource.password=`
   - Opcional: `spring.jpa.hibernate.ddl-auto=update` (ou utilize Flyway se desejar manter as migrações)
2. Com isso configurado, rode a aplicação com `mvn spring-boot:run`.

## Endpoints principais
Rotas base e métodos. Os corpos de request devem seguir os modelos abaixo.

- Usuário (`/usuario`)
  - POST `/usuario` cria usuário
    - Body: `{ "id": null, "nome": "João", "email": "joao@exemplo.com", "senha": "segredo123" }`
  - GET `/usuario` lista paginada
  - GET `/usuario/{id}` busca por id
  - DELETE `/usuario/{id}` remove

- Ponto Turístico (`/ponto-turistico`)
  - POST `/ponto-turistico`
    - Body: `{ "nome": "Praia X", "descricao": "Linda praia", "latitude": -8.05, "longitude": -34.88, "categoria": "PRAIA" }`
  - GET `/ponto-turistico` lista paginada
  - GET `/ponto-turistico/{id}` busca por id
  - GET `/ponto-turistico/categoria/{categoria}` filtra por categoria (enum em `models.pontoTuristico.Categoria`)
  - DELETE `/ponto-turistico/{id}`

- Marcar Visita (`/marca-visita`)
  - POST `/marca-visita`
    - Body: `{ "usuarioId": 1, "pontoTuristicoId": 10, "dataVisita": "2025-09-18" }`
    - Observação: a data de criação no backend é registrada como o momento atual (o campo `dataVisita` é validado, mas não é utilizado para persistência neste momento).
  - GET `/marca-visita` lista paginada
  - GET `/marca-visita/{id}`
  - GET `/marca-visita/usuario/{usuarioId}`
  - GET `/marca-visita/ponto-turistico/{pontoTuristicoId}`
  - DELETE `/marca-visita/{id}`

- Avaliação (`/avaliacao`)
  - POST `/avaliacao`
    - Body: `{ "usuarioId": 1, "pontoTuristicoId": 10, "nota": 5, "comentario": "Excelente!" }`
  - GET `/avaliacao` lista paginada
  - GET `/avaliacao/{id}`
  - GET `/avaliacao/usuario/{usuarioId}`
  - GET `/avaliacao/ponto-turistico/{pontoTuristicoId}`
  - DELETE `/avaliacao/{id}`

## Validações das requisições (Bean Validation)
Foram adicionadas anotações de validação nos DTOs de entrada (records):
- `UsuarioRequest`
  - `nome`: `@NotBlank`, `@Size(max=100)`
  - `email`: `@NotBlank`, `@Email`
  - `senha`: `@NotBlank`, `@Size(min=6, max=100)`
- `PontoTuristicoRequest`
  - `nome`: `@NotBlank`, `@Size(max=120)`
  - `descricao`: `@NotBlank`, `@Size(max=1000)`
  - `latitude`: `@NotNull`, `@Min(-90)`, `@Max(90)`
  - `longitude`: `@NotNull`, `@Min(-180)`, `@Max(180)`
  - `categoria`: `@NotNull`
- `MarcaVisitaRequest`
  - `usuarioId`: `@NotNull`
  - `pontoTuristicoId`: `@NotNull`
  - `dataVisita`: `@NotBlank`, `@Pattern(^\\d{4}-\\d{2}-\\d{2}$)`
- `AvaliacaoRequest`
  - `usuarioId`: `@NotNull`
  - `pontoTuristicoId`: `@NotNull`
  - `nota`: `@NotNull`, `@Min(1)`, `@Max(5)`
  - `comentario`: `@Size(max=500)`

Nos controllers, os parâmetros `@RequestBody` agora usam `@Valid` para disparar as validações automaticamente. Em caso de erro, o Spring devolverá 400 Bad Request com os detalhes.

## Convenções e boas práticas aplicadas
- DTOs de request com Bean Validation (jakarta.validation).
- Controllers retornam `ResponseEntity` e usam `@Valid` nos corpos.
- Mensagens de validação em português visando clareza para alunos.
- Projeto já inclui Flyway para migrações e Postgres via Docker Compose.

Sugestões futuras (não quebram o escopo deste exercício):
- Criar um `@ControllerAdvice` para padronizar respostas de erro (incluindo 404 de NotFound).
- Melhorar o uso de `dataVisita` no fluxo de marcação (persistir a data informada ou remover do request se não for usada).
- Substituir `RuntimeException` por exceções específicas e mapear para códigos HTTP adequados.

