package me.gnoyes.kakaoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class KakaoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakaoApiApplication.class, args);
    }

}
