# springboot集成dubbo
# 生产者
```
1.集成时的pom.xml,生产者，消费者都一样
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>0.2.1-SNAPSHOT</version>
        </dependency>

        <!-- Dubbo -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>dubbo</artifactId>
            <version>2.6.5</version>
        </dependency>

        <!-- Spring Context Extras -->
        <dependency>
            <groupId>com.alibaba.spring</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>1.0.2</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.apache.curator/curator-framework -->
        <dependency>
            <groupId>org.apache.curator</groupId>
            <artifactId>curator-framework</artifactId>
            <version>2.12.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
2.配置文件
dubbo:
  application:
    name: producer
    id: producer
  registry:
    address: zookeeper://127.0.0.1:2181
  scan:
    base-packages: com.example.producer.service
  protocol:
    port: 12345
    id: dubbo
    name: dubbo
 
3.需要发布的接口
@Service
public class ProducerServiceImpl implements ProducerService {
    @Override
    public String sayHello(String name) {
        return "hello," + name;
    }
}

4.springboot启动类
@SpringBootApplication
public class SpringBootProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProducerApplication.class, args);
    }

}
```

# 消费者
```
1.配置
dubbo:
  application:
    name: consumer
    id: consumer
  registry:
    address: zookeeper://127.0.0.1:2181
  protocol:
    port: 12344
    id: dubbo-consumer
    name: dubbo-consumer
    
2.生产者发布的接口（路径必须一致）
package com.example.producer.service;

public interface ProducerService {
    String sayHello(String name);
}

3.消费者使用
@Service
public class ConsumerService {

    @Reference
    ProducerService producerService;

    public void sayHello() {
        String s = producerService.sayHello("world");
        System.out.println(s);
    }
}

4.启动类
@SpringBootApplication
public class SpringBootConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootConsumerApplication.class, args);
	}

}
```
[dubbo git](https://github.com/apache/incubator-dubbo-spring-boot-project)
