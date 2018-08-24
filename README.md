# product-catalog-microservice

Steps to run product catalog service
1. Please download the zip named product-catalog-microservice.zip and unzip it on your
local system.
2. It is a maven java project using spring-boot, spring-boot jersey for rest service,
embedded H2 as database, spring-boot jdbc template.
3. Import it as a project in Eclipse IDE.
4. Execute it via maven command - mvn spring-boot:run
5. You can see few product add/search operations executed on startup in command line
logs.
6. Once started. You can check the h2 db at http://localhost:8080/h2. It will open h2
console to connect to embedded db and check if any product table exists or not.
7. You can do product operations like 
a) List All Products - Call this API from browser http://localhost/product/search/
b) Search Product by Product Type - Call this API from browser
http://localhost/product/search/{productType}. Replace {productType} with actual productType.
c) Add Product - Call this API http://localhost/product/add
send post request via any rest client and send json as post data like this 

{
"name" :"chicken", "productType": "Food" }
d) Remove Product - Call this API from browser http://localhost:8080/product/remove/{productName}. Replace {productName} with actual productname.


