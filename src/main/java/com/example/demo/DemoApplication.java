package com.example.demo;

import com.example.demo.common.argumenthandler.Auth;
import com.example.demo.common.argumenthandler.Entity;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableAspectJAutoProxy
public class DemoApplication {

    static {
        SpringDocUtils.getConfig()
                .addAnnotationsToIgnore(Auth.class)
                .addAnnotationsToIgnore(Entity.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
