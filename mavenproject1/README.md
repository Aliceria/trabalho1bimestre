# API de Jogos

Trabalho do primeiro bimestre: API REST com Spring Boot e Spring Web.
Os jogos ficam em uma lista no controller. Ao encerrar a aplicação, os dados são perdidos.

## Executar

Use um JDK 17 ou superior. Abra esta pasta como projeto Maven na IDE e execute
a classe Mavenproject1. Aguarde a aplicação iniciar na porta 8080.
Também pode executar pelo terminal nesta pasta:

```powershell
.\mvnw.cmd spring-boot:run
```

O terminal precisa ter JAVA_HOME apontando para o JDK.

## Testar no Postman

Importe o arquivo ApiJogos.postman_collection.json pelo botão Import.
Com a API rodando, execute Cadastrar jogo e depois Listar jogos.
As outras requisições já estão preenchidas. A variável id começa em 1;
ajuste para o ID retornado no cadastro se necessário.

| Método | Rota | Função |
| --- | --- | --- |
| GET | /jogo | Listar e filtrar |
| GET | /jogo/{id} | Buscar pelo ID |
| POST | /jogo | Cadastrar |
| PUT | /jogo/{id} | Atualizar |
| DELETE | /jogo/{id} | Excluir |

POST e PUT recebem no Body, em raw / JSON:

```json
{
  "nome": "Minecraft",
  "genero": "Sandbox",
  "plataforma": "PC",
  "preco": 99.9
}
```

O ID é gerado no controller. No PUT, o ID da URL identifica o jogo.
Nome, gênero e plataforma são obrigatórios; preço deve ser zero ou positivo.
Os filtros comparam o texto completo, sem diferenciar maiúsculas de minúsculas.

Exemplo dos três filtros juntos:

```text
http://localhost:8080/jogo?nome=Minecraft&genero=Sandbox&plataforma=PC
```

Respostas: 200 para consultas, cadastro e atualização; 204 para exclusão;
400 para dados inválidos; 404 quando o ID não existe.

## Sugestão para o vídeo (até 8 minutos)

1. Apresentar o tema e os campos de Jogo.
2. Mostrar a lista, o contador de IDs e as rotas no controller.
3. No Postman, cadastrar dois jogos diferentes e listar.
4. Buscar pelo ID e mostrar os três filtros, separados e combinados.
5. Atualizar um jogo e consultar para conferir.
6. Excluir e consultar o ID excluído para mostrar o 404.

Para a entrega, ainda é necessário publicar o projeto no Git e gravar o vídeo.


