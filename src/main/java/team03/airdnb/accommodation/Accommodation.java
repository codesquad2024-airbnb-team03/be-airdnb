package team03.airdnb.accommodation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team03.airdnb.accommodationAmenity.AccommodationAmenity;
import team03.airdnb.amenity.Amenity;
import team03.airdnb.common.BaseEntity;
import team03.airdnb.favorite.Favorite;
import team03.airdnb.reservation.Reservation;
import team03.airdnb.review.Review;
import team03.airdnb.user.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Accommodation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String profileImg;

    @Embedded
    private Address address;

    private Long price;
    private double averageGrade;
    private int maxHeadCount;
    private int bedCount;
    private int bedroomCount;
    private int bathroomCount;
    private double longitude; // 경도
    private double latitude; // 위도

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    private List<Favorite> favorites = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private User host;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccommodationAmenity> accommodationAmenities = new ArrayList<>();

    public void addAmenity(Amenity amenity) {
        if (accommodationAmenities == null) {
            accommodationAmenities = new ArrayList<>();
        }
        AccommodationAmenity accommodationAmenity = new AccommodationAmenity(this, amenity);
        this.accommodationAmenities.add(accommodationAmenity);
        amenity.getAccommodationAmenities().add(accommodationAmenity);
    }

    public void updateAmenities(List<Amenity> newAmenities) {
        // 기존 amenities 제거
//        for (AccommodationAmenity aa : new ArrayList<>(accommodationAmenities)) {
//            aa.getAmenity().getAccommodationAmenities().remove(aa);
//            accommodationAmenities.remove(aa);
//        }
        accommodationAmenities.clear();

        // 새로운 amenities 추가
        newAmenities.forEach(this::addAmenity);
//
//        System.out.println("accommodationAmenities.size()" + accommodationAmenities.size());
    }
}