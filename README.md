
#### Инструкция по сборке и запуску:

Сборка осуществляется с помощью gradle из корневой папки проекта: 
```
/.gradlew build
java -jar build/libs/reactive?calculator-0.0.1-SNAPSHOT.jar
```

Обращение к сервису происходит через url:
```
http://localhost:8080/api/count
```
Можно протестировать  через Postman
{
"code1": "function operation(param) {return ( param*5);}",
"code2":  "function operation(param) {return (45 + param);}",
"isAligned": 1,
"times": 20
}

#### Проверка реализованного сервиса:

Тестировать приложение возможно с помощью Swagger по следующему url:
```
http://localhost:8080/swagger-ui.html#/calculator-controller
```
curl -X POST "http://localhost:8080/api/count" -H "accept: */*" -H "Content-Type: application/json" -d 
"{ \"code1\": \"function operation(param) {return ( param*5);}\", 
\"code2\": \"function operation(param) {return (45 + param);}\", \"isAligned\": 1, \"times\": 10}"

curl -X POST "http://localhost:8080/api/count" -H "accept: */*" -H "Content-Type: application/json" -d
"{ \"code1\": \"function operation(param) {return ( param*5);}\",
\"code2\": \"function operation(param) {return (45 + param);}\", \"isAligned\": 0, \"times\": 10}"