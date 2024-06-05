package team03.airdnb.amenity;

import jakarta.persistence.*;
import team03.airdnb.accommodation.AccommodationAmenity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AMENITYS")
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "amenity", cascade = CascadeType.ALL)
    private List<AccommodationAmenity> accommodationAmenities = new ArrayList<>();
}
