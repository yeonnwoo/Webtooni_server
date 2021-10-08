package com.webtooni.webtooniverse.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000",
                "http://webtooniverse-host.s3-website.ap-northeast-2.amazonaws.com/",
                "http://34.64.100.68",
                "https://webtooni.co.kr",
                "https://api.webtooni.co.kr",
                "https://www.webtooni.co.kr",
                "http://34.64.220.67",
                "http://www.webtooni.co.kr",
                "http://webtooni.co.kr"
                )
            .allowedMethods(
                HttpMethod.GET.name(),
                HttpMethod.HEAD.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name());
    }

}
