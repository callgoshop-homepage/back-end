package org.example.global.mvcconfig;

import lombok.RequiredArgsConstructor;
import org.example.global.jwt.JwtProvider;
import org.example.global.secretkey.CreateSecretKey;
import org.example.global.tokenverify.JwtInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:application.yml")
public class MvcConfig implements WebMvcConfigurer {

    private final CreateSecretKey createSecretKey;

    private final JwtProvider jwtProvider;

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor(jwtProvider))
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/api/v1/member/join",
                        "/api/v1/member/login",
                        "/api/v1/member/findId",
                        "/api/v1/member/findPassword"
                );  // 이 인터셉터를 모든 경로에 적용합니다.
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/productregistration/**")
                .addResourceLocations("file:///Users/choegyeonghyeon/Desktop/callgo shop 프로젝트/front-app/static/img/");
    }
}