package me.gnoyes.kakaoapi.domain;

public enum AddressType {
    ROAD("도로명주소","ROAD"),
    REGION("지번주소", "REGION");

    private final String desc;
    private final String code;

    AddressType(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }
}
