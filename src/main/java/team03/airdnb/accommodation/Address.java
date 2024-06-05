package team03.airdnb.accommodation;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String firstAddress; // 광역시/도
    private String secondAddress; // 시/군/구
    private String thirdAddress; // 도로명
}
