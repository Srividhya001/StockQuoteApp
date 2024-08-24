# Getting Started

## API Documentation - Swagger
- Run the code in local - (default port 8080 )
- In browser access the api documentation in the below link

`http://localhost:8080/swagger-ui/index.html`

## Spring security is enabled for getStockQuote and getAllQuote apis
- Dependency is SQL DB, download and run the sql server.
- Create the schema - "security_db"
- update the apiKey in application.properties by registering to alpha.
- update the running SQL local server in application.properties as shown below.

`spring.datasource.url=jdbc:mysql://localhost:3306/security_db`

- There is an api exposed to register user or admin roles and creds
sample request for register user --> roles can have either user or admin (case sensitive)
`{
"id": 0,
"userName": "User1",
"password": "Password1",
"active": true,
"roles": "user"
}`

- user role can access getStockQuote
- admin role can access getAllQuote and getStockQuote
- All can access the register user api
