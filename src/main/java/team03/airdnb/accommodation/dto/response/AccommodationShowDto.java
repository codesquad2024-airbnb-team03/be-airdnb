package team03.airdnb.accommodation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team03.airdnb.accommodation.Accommodation;
import team03.airdnb.accommodation.Address;
import team03.airdnb.review.Review;
import team03.airdnb.review.dto.response.ReviewShowDto;
import team03.airdnb.user.dto.response.UserShowDto;

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
    private int bedCount;
    private int bedroomCount;
    private int bathroomCount;
    private List<String> amenities;
    private double averageGrade;
    private int reviewCount;
    private List<ReviewShowDto> reviews;
    private UserShowDto host;

    public static AccommodationShowDto of(Accommodation accommodation, Long fee, List<String> amenities) {
        return new AccommodationShowDto(
                accommodation.getName(),
                accommodation.getProfileImg(),
                accommodation.getAddress(),
                accommodation.getPrice(),
                fee,
                accommodation.getHeadCount(),
                accommodation.getBedCount(),
                accommodation.getBedroomCount(),
                accommodation.getBathroomCount(),
                amenities,
                accommodation.getAverageGrade(),
                accommodation.getReviews().size(),
                reviewsToDto(accommodation.getReviews()),
                UserShowDto.of(accommodation.getHost())
        );
    }

    // List<reviews>를 List<ReviewShowDto>로 변환하기
    private static List<ReviewShowDto> reviewsToDto(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewShowDto::of)
                .toList();
    }
}