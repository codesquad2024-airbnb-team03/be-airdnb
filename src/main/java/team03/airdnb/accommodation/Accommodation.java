package team03.airdnb.accommodation;

import jakarta.persistence.*;
import team03.airdnb.like.Like;
import team03.airdnb.reservation.Reservation;
import team03.airdnb.review.Review;
import team03.airdnb.user.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ACCOMMODATIONS")
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String profileImg;

    @Embedded
    private Address address;

    private Long price;
    private int headCount;
    private int bedCount;
    private int bedroomCount;
    private int bathroomCount;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private User host;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    private List<AccommodationAmenity> accommodationAmenities = new ArrayList<>();
}
