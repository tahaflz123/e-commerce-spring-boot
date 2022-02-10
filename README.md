# e-commerce-spring-boot




POST http://localhost:8080/user/register RequestBody {name,surname,email,password} all String

POST http://localhost:8080/user/login response jwt token

#for /product endpoint

POST http://localhost:8080/product/create RequestBody {String name,Sting about,Double price,Integer stock}

GET http://localhost:8080/product?id=id Long

GET http://localhost:8080/product/ for all products

GET http://localhost:8080/product/user?id=userId Long response is user's created products

DELETE http://localhost:8080/product/delete?id=productId deletes product with id, response is boolean

POST http://localhost:8080/product/search?q=Keyword filters product by name

GET http://localhost:8080/product/category/{CATEGORY} also you can add ?q= param for filter after than category variable for example: FOOD?q=Keyword
categories: {FOOD,TECHNOLOGY,FASHION,TOY,BOOK}

PUT http://localhost:8080/product/update/stock?prid=productId:Long&stock=Integer adds stock to product


not finished yet...
