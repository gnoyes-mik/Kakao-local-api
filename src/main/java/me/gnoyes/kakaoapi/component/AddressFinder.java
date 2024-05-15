package me.gnoyes.kakaoapi.component;

import me.gnoyes.kakaoapi.domain.AddressInfo;

import java.util.Optional;

public interface AddressFinder {

    /**
     * 주소 검색을 통해 상세 주소 정보를 불러온다. <br>
     * 검색 결과가 여러개인 경우 최상단 1개만 불러온다.(검색 결과가 존재하지 않을 수도 있다.)
     * @param searchAddress 검색할 주소
     * @return Optional AddressInfo
     */
    Optional<AddressInfo> findBy(String searchAddress);
}
