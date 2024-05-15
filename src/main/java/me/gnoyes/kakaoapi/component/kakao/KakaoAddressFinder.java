package me.gnoyes.kakaoapi.component.kakao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.gnoyes.kakaoapi.client.KakaoApiClient;
import me.gnoyes.kakaoapi.component.AddressFinder;
import me.gnoyes.kakaoapi.domain.AddressInfo;
import me.gnoyes.kakaoapi.domain.AddressType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoAddressFinder implements AddressFinder {

    private final KakaoApiClient kakaoApiClient;

    @Override
    public Optional<AddressInfo> findBy(String searchAddress) {
        KaKaoApi.LocalResult searchResult = kakaoApiClient.searchBy(searchAddress);
        log.info(searchResult.toString());
        return toAddressInfo(searchResult);
    }

    private Optional<AddressInfo> toAddressInfo(KaKaoApi.LocalResult searchResult) {
        if (!searchResult.hasResults()) {
            return Optional.empty();
        }
        KaKaoApi.Document document = searchResult.first();
        KaKaoApi.Address regionAddress = document.getRegionAddressDetail();
        KaKaoApi.RoadAddress roadAddress = document.getRoadAddressDetail();
        String zoneNo = document.getZoneNo();

        AddressInfo addressInfo = AddressInfo.builder()
                .regionAddress(regionAddress.getAddress())
                .roadAddress(roadAddress.getAddress())
                .zipCode(zoneNo)
                .searchAddress(document.getAddress())
                .searchAddressType(toAddressType(document.getAddressType()))
                .build();

        return Optional.of(addressInfo);
    }

    private AddressType toAddressType(KaKaoApi.KakaoAddressType type) {
        switch (type) {
            case REGION, REGION_ADDR -> {
                return AddressType.REGION;
            }
            case ROAD, ROAD_ADDR -> {
                return AddressType.ROAD;
            }
            default -> throw new RuntimeException("KakaoAddressType(%s)에서 AddressType으로 변환에 실패하였습니다".formatted(type));
        }
    }
}
