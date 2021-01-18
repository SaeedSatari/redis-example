# redis-example

An Example for integrating Spring boot and Redis. Redis is an in-memory data structure store, used as a distributed, in-memory key–value database, cache and message broker, with optional durability.

A Cache which instead of evicting an entry on expiration keeps it until it is able to get new Data.

Technically this is done via Spring AOP.
We create an Around Advice which is guarding our real invocation.

It was created as a drop-in replacement for @Cacheable.

## Getting started

### Prerequisites:

- Java 8
- Spring Boot 2.4.1
- Maven as our project management and comprehension tool

### Tools:

- IntelliJ IDEA
- Lombok 

### Installing Redis on windows:

There are several ways to install Redis on your machine. Here, I used chocolaty to install:

- Run cmd.exe
- Run the following command to install chocolaty:

```cmd
    @"%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command "[System.Net.ServicePointManager]::SecurityProtocol = 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))" && SET "PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin"
```

- To install Redis using chocolaty, run the following command:

```bash
    choco install redis-64
```

### Clone the repository

```bash
    git clone https://github.com/SaeedSatari/redis-example.git
```

### Run the app using maven

```bash
    cd redis-example
    mvn spring-boot:run
```

### Testing

Call http://localhost:8080/ to create a sample order, then you can see these logs on the console:

- Order ir.ssatari.redis.service.OrderService.getOrder(int,String,String) executed in 1076 ms
- Result: Order(id=42, orderNumber=17259)

After that, refresh your browser, and you can see on the console that the data retrieved from cache:
- Cache key: orders_42CacheSuffix
- Reading from cache ...Order(id=42, orderNumber=92803)
- Order ir.ssatari.redis.service.OrderService.getOrder(int,String,String) executed in 3 ms
- Result: Order(id=42, orderNumber=92803)

After that, call http://localhost:8080/another ,and you can see these logs on the console:

- AnotherDTO ir.ssatari.redis.service.OrderService.getAnother(int,String,String) executed in 1018 ms
- Result: AnotherDTO(id=42, someThing=Test, anotherThing=CacheSuffix)

After that, refresh your browser, and you can see on the console that the data retrieved from cache:

- Cache key: another_42CacheSuffix
- Reading from cache ...AnotherDTO(id=42, someThing=Test, anotherThing=CacheSuffix)
- AnotherDTO ir.ssatari.redis.service.OrderService.getAnother(int,String,String) executed in 2 ms
- Result: AnotherDTO(id=42, someThing=Test, anotherThing=CacheSuffix)

### Usage

The usage is pretty similar to @Cacheable known from the Spring Cache Abstraction.

```java
@Service
public class OrderService {

    @TwoLayerRedisCacheable(firstLayerTtl = 1L, secondLayerTtl = 5L, key = "'orders_'.concat(#id).concat(#another)")
    public Order getOrder(int id, String other, String another) {
        Order order = getOrderViaHTTP(id);
        return order;
    }
}
```

I hope this example helps developers who want to learn more about Redis and Spring Boot integrity.
If you have any questions, please feel free to reach me by email: saeedsatari93@gmail.com 
