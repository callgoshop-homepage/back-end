package org.example.global.mvcconfig;

import lombok.RequiredArgsConstructor;
import org.example.global.jwt.JwtProvider;
import org.example.global.secretkey.CreateSecretKey;
import org.example.global.tokenverify.JwtInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.*;

import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
//@PropertySource("classpath:application-dev.yml")
public class MvcConfig implements WebMvcConfigurer {

    @Value("${image.upload.dir}")
    private String uploadDir;

    private final CreateSecretKey createSecretKey;

    private final JwtProvider jwtProvider;

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "https://callgomall.com/")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor(jwtProvider))
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/",
                        "/**",
                        "/api/v1/member/join",
                        "/api/v1/member/login",
                        "/api/v1/member/findId",
                        "/api/v1/member/findPassword"
                );  // 이 인터셉터를 모든 경로에 적용합니다.
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String path = uploadDir;

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
        registry.addResourceHandler("/productregistration/**")
                .addResourceLocations(path);
        registry.addResourceHandler("/mainview/**")
                .addResourceLocations(path);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.addViewController("/shoppingbasket").setViewName("forward:/index.html");
        registry.addViewController("/maincategoryview").setViewName("forward:/index.html");
        registry.addViewController("/productregistration").setViewName("forward:/index.html");
        registry.addViewController("/admin").setViewName("forward:/index.html");
        registry.addViewController("/detail").setViewName("forward:/index.html");
    }
}