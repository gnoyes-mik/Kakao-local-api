package me.gnoyes.kakaoapi.component;

import me.gnoyes.kakaoapi.component.kakao.KaKaoApi;
import me.gnoyes.kakaoapi.domain.AddressInfo;
import me.gnoyes.kakaoapi.domain.AddressType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class KakaoAddressFinderTest {

    @Autowired
    AddressFinder KakaoAddressFinder;

    @Test
    @DisplayName("도로명 주소를 넣었을 때 우편번호, 주소타입을 알 수 있다")
    void findByTest_1() {
        // given
        String address = "서울 강남구 봉은사로 327, 11층 올라핀테크";

        // when
        Optional<AddressInfo> optAddressInfo = KakaoAddressFinder.findBy(address);

        // then
        Assertions.assertTrue(optAddressInfo.isPresent());
        AddressInfo addressInfo = optAddressInfo.get();
        Assertions.assertEquals(AddressType.ROAD , addressInfo.getSearchAddressType());
        Assertions.assertEquals("06103", addressInfo.getZipCode());
    }

    @Test
    @DisplayName("지번 주소를 넣었을 때 우편번호, 주소타입을 알 수 있다")
    void findByTest_2() {
        // given
        String address = "논현동 278-19";

        // when
        Optional<AddressInfo> optAddressInfo = KakaoAddressFinder.findBy(address);

        // then
        Assertions.assertTrue(optAddressInfo.isPresent());
        AddressInfo addressInfo = optAddressInfo.get();
        Assertions.assertEquals(AddressType.REGION , addressInfo.getSearchAddressType());
        Assertions.assertEquals("06103", addressInfo.getZipCode());
    }

}