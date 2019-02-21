# Starwars-api
Consumo da API Starwars, disponível em: https://swapi.co/

O objetivo dessa aplicação é disponibilizar uma API Rest que consulte os planetas da api swap.co e armazene algumas
informações no banco de dados, bem como prover métodos de busca e remoção.



Configurações Banco de Dados:
-----------------------------
driverClassName: com.mysql.jdbc.Driver

url: jdbc:mysql://localhost:3306/b2w

username: root

password: sa1234

Rodando o sistema com maven
---------------------------
Entrar no diretorio do projeto
		
executar a aplicação com o seguinte comando: mvn spring-boot:run

	
Utilizando o swagger para testar a API
---------------------------
Após iniciar a aplicação, acesse em um navegador a seguinte url:
http://localhost:8088/swagger-ui.html

Utilização do Jacoco para metricas de cobertura de testes unitários
-------------------------------------------------------------------
executar o seguinte comando: mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Dmaven.test.failure.ignore=true

relatório estará disponível em:  {diretorioProjeto}/target/site/jacoco/index.html
	

