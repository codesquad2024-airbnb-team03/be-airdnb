package team03.airdnb.accommodation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team03.airdnb.accommodation.Address;

import java.util.List;

@Getter
@AllArgsConstructor
public class AccommodationListDto {

    private String name;
    private String profileImg;
    private Address address;
    private Long price;
    private int headcount;
    private int bed_count;
    private int bedroom_count;
    private int bathroom_count;
    private List<String> amenities;
    private double averageGrade;
    private int reviewCount;
}
