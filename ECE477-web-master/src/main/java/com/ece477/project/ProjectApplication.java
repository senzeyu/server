package com.ece477.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import com.ece477.project.TCP_server;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController

@SpringBootApplication
@MapperScan("com.ece477.project.mapper")
public class ProjectApplication extends TCP_server{
    static public TCP_server test;
    @RequestMapping("/")
    String index() {
        return "Hello Spring Boot";
    }

    public static void main(String[] args) throws IOException {
        test = new TCP_server();

        System.out.println("in main");
        SpringApplication.run(ProjectApplication.class, args);
        test.tcp_server();
        System.out.println("Main request: "+ test.request);

    }
}

