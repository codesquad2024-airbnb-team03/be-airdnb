package team03.airdnb.AccommodationAmenity;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationAmenityRepository extends JpaRepository<AccommodationAmenity, Long> {
    List<AccommodationAmenity> findByAccommodationId(Long accommodationId);
}
