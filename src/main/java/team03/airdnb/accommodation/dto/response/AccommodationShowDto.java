package team03.airdnb.accommodation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team03.airdnb.accommodation.Accommodation;
import team03.airdnb.accommodation.Address;
import team03.airdnb.review.Review;
import team03.airdnb.user.User;

import java.util.List;

@Getter
@AllArgsConstructor
public class AccommodationShowDto {

    private String name;
    private String profileImg;
    private Address address;
    private Long price;
    private Long fee;
    private int headcount;
    private int bed_count;
    private int bedroom_count;
    private int bathroom_count;
    private List<String> amenities;
    private double averageGrade;
    private int reviewCount;
    private List<Review> reviews;
    private User host;

    public static AccommodationShowDto of(Accommodation accommodation, Long fee,
                                          List<String> amenities,
                                          double averageGrade, int reviewCount, List<Review> reviews) {
        return new AccommodationShowDto(accommodation.getName(), accommodation.getProfileImg(),
                accommodation.getAddress(), accommodation.getPrice(), fee, accommodation.getHeadCount(),
                accommodation.getBedCount(), accommodation.getBedroomCount(), accommodation.getBathroomCount(),
                amenities,
                averageGrade, reviewCount, reviews, accommodation.getHost()
        );
    }
}