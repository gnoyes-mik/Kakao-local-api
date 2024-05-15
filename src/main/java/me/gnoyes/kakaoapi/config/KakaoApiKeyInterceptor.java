package me.gnoyes.kakaoapi.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoApiKeyInterceptor implements RequestInterceptor {

    @Value("${kakao.api.key}")
    private String REST_API_KEY;

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", "KakaoAK " + REST_API_KEY);
    }
}