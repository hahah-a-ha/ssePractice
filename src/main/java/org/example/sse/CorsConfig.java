package org.example.sse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/v1/sse/broadcast") // 허용할 엔드포인트를 지정
                .allowedOrigins("http://localhost:3000") // 허용할 오리진을 지정
                .allowedMethods("POST"); // 허용할 HTTP 메서드를 지정
    }
}
