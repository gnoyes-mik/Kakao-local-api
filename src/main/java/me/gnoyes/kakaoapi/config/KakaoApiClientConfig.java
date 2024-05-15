package me.gnoyes.kakaoapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KakaoApiClientConfig {

    @Bean
    public KakaoApiKeyInterceptor interceptor() {
        return new KakaoApiKeyInterceptor();
    }
}
