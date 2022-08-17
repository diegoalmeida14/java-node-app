# java-node-app

## Java

Java Version: 17

MVN Version: 3.8.6

### to install:

```bash
cd inventory

mvn install

```
To Run app: 
run.sh


Swagger: http://localhost:8080/swagger-ui.html.

/POST http://localhost:8080/api/produto/

/POST http://localhost:8080/api/clientes

/POST http://localhost:8080/api/aluguel/
## Node

after **your Java aplication is running**, open new terminal and run: 



```bash
cd ../cliente-test
yarn install
yarn dev
```


Main apis:

/PUT http://localhost:4000/api/devolucao/{aluguel_id}

/POST  http://localhost:4000/api/search

/POST  http://localhost:8080/api/aluguel/

/POST http://localhost:4000/api/aluguel/

/POST http://localhost:4000/api/venda/

![Alt text](arquitetura-node-java.jpg?raw=true "Title")
