package org.example.sse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CorsConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/v1/sse/subscribe/**") // 허용할 엔드포인트를 지정
//                .allowedOrigins("http://3.36.130.233:8080") // 허용할 오리진을 지정
                .allowedOriginPatterns("*")
                .allowedMethods("GET"); // 허용할 HTTP 메서드를 지정
    }
}
