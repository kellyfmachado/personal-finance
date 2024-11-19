
# API - Personal Finance

## Sobre o projeto

Esta API foi desenvolvida para auxiliar no controle de finanças pessoais, funcionando como uma carteira digital que permite o acompanhamento detalhado do saldo e das transações financeiras do usuário, promovendo uma gestão mais eficaz e consciente de seus recursos.

## Tecnologias utilizadas

![Java](https://img.shields.io/badge/-Java-000?style=for-the-badge&logo=openJDK&logoColor=8b2ea9) 
![Spring Boot](https://img.shields.io/badge/-Spring_Boot-000?style=for-the-badge&logo=springboot&logoColor=8b2ea9)
![Spring Security](https://img.shields.io/badge/-Spring_Security-000?style=for-the-badge&logo=springsecurity&logoColor=8b2ea9)
![PostreSQL](https://img.shields.io/badge/-PostgreSQL-000?style=for-the-badge&logo=postgresql&logoColor=8b2ea9)
![Auth0](https://img.shields.io/badge/-Auth0-000?style=for-the-badge&logo=auth0&logoColor=8b2ea9)
![Lombok](https://img.shields.io/badge/-Lombok-000?style=for-the-badge&logo=openJDK&logoColor=8b2ea9)
![JPA](https://img.shields.io/badge/-JPA-000?style=for-the-badge&logo=openJDK&logoColor=8b2ea9)
![Maven](https://img.shields.io/badge/-Maven-000?style=for-the-badge&logo=apachemaven&logoColor=8b2ea9)
![Postman](https://img.shields.io/badge/-Postman-000?style=for-the-badge&logo=postman&logoColor=8b2ea9)

## Funcionalidades

Autenticação:
- **Registrar usuário** - POST /auth/register
- **Login** - POST /auth/login

Usuário:
- **Atualizar usuário** - PUT   /user/update
- **Listar usuários (Apenas ADMIN)** - GET /user/list
- **Retornar usuário autenticado** - GET /user/authenticated
- **Deletar usuário** - DELETE /user/delete

Transações:
- **Criar transação** - POST /transaction/add
- **Atualizar transação** - PUT /transaction/update
- **Listar todas as transações** - GET /transaction/list
- **Listar transações por categoria** - GET /transaction/listByCategory/{id}
- **Deletar transação** - DELETE /transaction/delete/{id}

Categorias:
- **Criar categoria** - POST /category/add
- **Atualizar categoria** - PUT /category/update
- **Listar categorias** - GET /category/list
- **Deletar categoria** - DELETE /category/delete/{id}


## Estrutura de pastas

  
```
/src
	/main
		/java
			/java_project
				/personal_finance 	   
					/controller 	   # Controladores REST
					/cors			   # Modelos de dados do PostgreSQL
					/dto			   # Data Transfer Objects (DTOs)
					/enums 		  	   # Enumeradores para definição de roles e status
					/model 			   # Entidades do banco de dados
					/repository 	   # Repositórios para acesso a dados (JPA)
					/security 		   # Configurações de segurança e autenticação
					/service 		   # Lógica de negócio e serviços
		/resources
			/application.properties    # Credenciais do banco de dados
pom.xml								   # Gerenciamento de dependências

```  

## Rodando localmente

  
**1º Passo** - Clonar o repositório

- Clone o repositório em sua máquina local:

```bash
git clone https://github.com/kellyfmachado/personal-finance
```

**2º Passo** - Configurar o ambiente
 
- Verifique se tem o Java 17 instalado:

```bash
java -version
```
Caso contrário, baixe o JDK 17 [aqui](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html). 
- Depois, verifique se tem o Maven instalado:
```bash
mvn -version
```
Caso não tenha, siga as instruções no [site oficial do Maven](https://maven.apache.org/install.html).

**3º Passo** - Configurar o banco de dados

- Instale o PostgreSQL (se ainda não estiver instalado). Baixe o PostgreSQL [aqui](https://www.postgresql.org/download/).

- Acesse  o terminal do PostgreSQL:

```bash
psql -U <SEU_USUARIO>
```
- Crie o banco de dados e um usuário, se necessário:

```bash
CREATE DATABASE personal_finance;
CREATE USER finance_user WITH ENCRYPTED PASSWORD 'finance_password';
GRANT ALL PRIVILEGES ON DATABASE personal_finance TO finance_user;
```  
  - Configure as credenciais no arquivo `application.properties`, no diretório `src/main/resources`:
```bash  
spring.datasource.url=jdbc:postgresql://localhost:5432/personal_finance
spring.datasource.username=finance_user
spring.datasource.password=finance_password
spring.jpa.hibernate.ddl-auto=update
```
  **4º Passo** - Instalar dependências
  
  - No diretório do projeto, execute:
  
  ```bash  
mvn clean install
```

  **5º Passo** - Rodar o servidor
- Execute o comando para iniciar o servidor:
```bash
mvn spring-boot:run
```
- Se tudo estiver configurado corretamente, o servidor estará disponível no endereço:
```bash
http://localhost:8080
```
  **6º Passo** - Testar a API
  
  - Você pode testar os endpoints utilizando o **Postman** ou outra ferramenta de testes. 
