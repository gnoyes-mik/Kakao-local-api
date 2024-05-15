package me.gnoyes.kakaoapi.client;

import me.gnoyes.kakaoapi.config.KakaoApiKeyInterceptor;
import me.gnoyes.kakaoapi.component.kakao.KaKaoApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "KakaoApiClient",url = "${kakao.api.url}", configuration = KakaoApiKeyInterceptor.class)
public interface KakaoApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v2/local/search/address.JSON")
    KaKaoApi.LocalResult searchBy(@RequestParam("query") String address);
}
