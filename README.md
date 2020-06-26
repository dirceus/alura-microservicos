##### Construindo as aplicações

Acessar os projetos **config-server** e **eureka-service**, e em cada um desses projetos executar o comando abaixo para criar os executáveis das aplicações:

> ./mvnw package

Para os projetos **fornecedor** e **loja**, o comando a ser executado é:

```
./mvnw package -Dskiptest -Pdocker
```

###### Criando as imagens do docker de cada aplicação

**CONFIG SERVER**

```
docker build -f config-server.dockerfile -t dirceus/config-server .
```

**EUREKA SERVER**

```
docker build -f eureka-server.dockerfile -t dirceus/eureka-server .
```

**Microserviço Fornecedor**

```
docker build -f fornecedor.dockerfile -t dirceus/fornecedor-microservico .
```

**Microserviço Loja**

```
docker build -f loja.dockerfile -t dirceus/loja-microservico .
```

##### Subindo a aplicação com o Docker-Compose

```
docker-compose up
```
