package com.playdata.front_admin;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /images/** 패턴으로 요청된 URL을 C:/EasyStay/img/user/ 경로로 매핑
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///C:/EasyStay/img/user/");

        // 추가적으로 다른 정적 리소스를 설정할 수 있음
        // 예: registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
