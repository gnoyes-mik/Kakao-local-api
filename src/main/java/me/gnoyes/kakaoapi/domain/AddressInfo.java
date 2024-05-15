package me.gnoyes.kakaoapi.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressInfo {
    private String regionAddress;
    private String roadAddress;
    private String zipCode;

    private String searchAddress;
    private AddressType searchAddressType;

    // 필요 시 추가 ...
}
