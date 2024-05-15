package me.gnoyes.kakaoapi.component.kakao;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class KaKaoApi {

    /**
     * Kakao Local API 응답 수신용 DTO
     **/
    @Getter
    @ToString
    public static class LocalResult {
        private Meta meta;
        private List<Document> documents;

        public boolean hasResults() {
            return !documents.isEmpty();
        }

        public Document first() {
            return documents.get(0);
        }
    }

    @Getter
    @ToString
    public static class Document {
        private String address_name; // 전체 지번 주소 또는 저체 도로명 주소, 입력에 따라 결정됨
        private KakaoAddressType address_type; // address_name의 값의 타입 REGION(지명)/ROAD(도로명)/REGION_ADDR(지번 주소)/ROAD_ADDR(도로명 주소)
        private String x; // X 좌표값, 경위도인 경우 경도(longitude)
        private String y; // Y 좌표값, 경위도인 경우 위도(latitude)
        private Address address; // 지번 주소 상세 정보
        private RoadAddress road_address; // 도로명 주소 상세 정보

        public String getAddress() {
            return address_name;
        }

        public KakaoAddressType getAddressType() {
            if (address_type == KakaoAddressType.REGION || address_type == KakaoAddressType.REGION_ADDR) {
                return KakaoAddressType.REGION_ADDR;
            }
            if (address_type == KakaoAddressType.ROAD || address_type == KakaoAddressType.ROAD_ADDR) {
                return KakaoAddressType.ROAD_ADDR;
            }
            throw new RuntimeException("타입이 존재하지 않습니다.");
        }

        /**
         * 우편번호 6자리가 deprecated 되어 도로명 주소에있는 국가 기초구역번호(zone_no)를 사용해야 함
         **/
        public String getZoneNo() {
            RoadAddress roadAddress = getRoadAddressDetail();
            if (roadAddress == null) {
                throw new RuntimeException("도로명 주소 결과가 존재하지 않습니다.");
            }
            return roadAddress.getZoneNo();
        }

        public Address getRegionAddressDetail() {
            return address;
        }

        public RoadAddress getRoadAddressDetail() {
            return road_address;
        }
    }

    @Getter
    @ToString
    public static class Address {
        private String address_name; // 전체 지번 주소
        private String region_1depth_name; // 지역 1 Depth, 시도 단위
        private String region_2depth_name; // 지역 2 Depth, 구 단위
        private String region_3depth_name; // 지역 3 Depth, 동 단위
        private String region_3depth_h_name; // 지역 3 Depth, 행정동 명칭
        private String h_code; // 행정 코드
        private String b_code; // 법정 코드
        private String mountain_yn; // 산 여부, Y 또는 N
        private String main_address_no; // 지번 주번지
        private String sub_address_no; // 지번 부번지, 없을 경우 빈 문자열("") 반환
        private String x; // X 좌표값, 경위도인 경우 경도(longitude)
        private String y; // Y 좌표값, 경위도인 경우 위도(latitude)

        public String getAddress() {
            return address_name;
        }
    }

    @Getter
    @ToString
    public static class RoadAddress {
        private String address_name; // 전체 도로명 주소
        private String region_1depth_name; // 지역명1
        private String region_2depth_name; // 지역명2
        private String region_3depth_name; // 지역명3
        private String road_name; // 도로명
        private String underground_yn; // 지하여부, Y 또는 N
        private String main_building_no; // 건물 본번
        private String sub_building_no; // 건물 부번, 없을 경우 빈 문자열("") 반환
        private String building_name; // 건물 이름
        private String zone_no; // 우편번호(5자리)
        private String x; // X 좌표값, 경위도인 경우 경도(longitude)
        private String y; // Y 좌표값, 경위도인 경우 위도(latitude)

        public String getAddress() {
            return address_name;
        }

        public String getZoneNo() {
            return zone_no;
        }
    }

    @Getter
    @ToString
    public static class Meta {
        private boolean is_end; // 현재 페이지가 마지막 페이지인지 여부(값이 false면 다음 요청 시 page 값을 증가시켜 다음 페이지 요청 가능)
        private int pageable_count; // total_count 중 노출 가능 문서 수(최대: 45)
        private int total_count; // 검색어에 검색된 문서 수
    }

    public enum KakaoAddressType {
        REGION("지명"),
        ROAD("도로명"),
        REGION_ADDR("지번 주소"),
        ROAD_ADDR("도로명 주소");

        private String desc;

        KakaoAddressType(String desc) {
            this.desc = desc;
        }
    }
}
