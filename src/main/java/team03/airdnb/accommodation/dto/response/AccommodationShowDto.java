package team03.airdnb.accommodation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team03.airdnb.accommodation.Accommodation;
import team03.airdnb.accommodation.Address;
import team03.airdnb.accommodationAmenity.AccommodationAmenity;
import team03.airdnb.reservation.Reservation;
import team03.airdnb.review.Review;
import team03.airdnb.review.dto.response.ReviewShowDto;
import team03.airdnb.user.dto.response.UserShowDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class AccommodationShowDto {

    private Long id;
    private String name;
    private String profileImg;
    private Address address;
    private Long price;
    private int maxHeadCount;
    private int bedCount;
    private int bedroomCount;
    private int bathroomCount;
    private double longitude; // 경도
    private double latitude; // 위도
    private List<String> amenities;
    private double averageGrade;
    private int reviewCount;
    private List<ReviewShowDto> reviews;
    private UserShowDto host;
    private List<LocalDate[]> reservedDates;

    public static AccommodationShowDto of(Accommodation accommodation, List<AccommodationAmenity> accommodationAmenities) {
        return new AccommodationShowDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getProfileImg(),
                accommodation.getAddress(),
                accommodation.getPrice(),
                accommodation.getMaxHeadCount(),
                accommodation.getBedCount(),
                accommodation.getBedroomCount(),
                accommodation.getBathroomCount(),
                accommodation.getLongitude(),
                accommodation.getLatitude(),
                amenitiesToStringList(accommodationAmenities),
                accommodation.getAverageGrade(),
                accommodation.getReviews().size(),
                reviewsToDto(accommodation.getReviews()),
                UserShowDto.of(accommodation.getHost()),
                getReservedDates(accommodation.getReservations())
        );
    }

    // List<reviews>를 List<ReviewShowDto>로 변환하기
    private static List<ReviewShowDto> reviewsToDto(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewShowDto::of)
                .toList();
    }

    // 숙소의 amenity를 모두 string(amenity name) 리스트로 변환하기
    private static List<String> amenitiesToStringList(List<AccommodationAmenity> accommodationAmenities) {
        return accommodationAmenities.stream()
                .map(accommodationAmenity -> accommodationAmenity.getAmenity().getName())
                .collect(Collectors.toList());
    }

    public static List<LocalDate[]> getReservedDates(List<Reservation> reservations) {
        List<LocalDate[]> reservedDates = new ArrayList<>();
        for (Reservation reservation : reservations) {
            LocalDate[] dates = new LocalDate[2];
            dates[0] = reservation.getCheckIn();
            dates[1] = reservation.getCheckOut();
            reservedDates.add(dates);
        }
        return reservedDates;
    }
}