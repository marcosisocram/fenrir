# Projeto Rinha Backend

## Stack

- Java 17
- GraalVM
- MongoDB
- Redis
- Nginx

## Como gerar o nativo

```shell
./mvnw -Pnative native:compile
```


## Como gerar a imagem docker

```shell
./mvnw -Pnative spring-boot:build-image
```

## Dificuldades

- Projeto inicialmente foi feito com java 20, mas deu erro ao buscar bibliotecas durante a geração de imagem docker, solução foi usar java 17

## Conhecimentos adquiridos

- Nginx
- Spring Boot Native
- Gatling

