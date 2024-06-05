package team03.airdnb.accommodation;

import jakarta.persistence.*;
import team03.airdnb.amenity.Amenity;

@Entity
@Table(name = "ACCOMMODATIONS_AMENITY")
public class AccommodationAmenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amenity_id")
    private Amenity amenity;
}
