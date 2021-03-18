package com.arcs.cibus.server;

import com.arcs.cibus.server.domain.User;
import com.sun.org.apache.xml.internal.serializer.utils.BoolStack;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        System.out.println(args);
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
