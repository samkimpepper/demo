package com.example.demo.config;

import com.example.demo.common.argumenthandler.AuthArgumentHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import com.example.demo.common.argumenthandler.EntityArgumentHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final EntityArgumentHandler entityArgumentHandler;
    private final AuthArgumentHandler userArgumentHandler;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentHandler);
        argumentResolvers.add(entityArgumentHandler);
    }
}
