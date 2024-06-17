package team03.airdnb.accommodationAmenity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.airdnb.accommodation.Accommodation;
import team03.airdnb.accommodation.AccommodationRepository;
import team03.airdnb.amenity.Amenity;
import team03.airdnb.amenity.AmenityRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class AccommodationAmenityService {

    private final AccommodationAmenityRepository accommodationAmenityRepository;
    private final AccommodationRepository accommodationRepository;
    private final AmenityRepository amenityRepository;

    public Long createAccommodationAmenity(Long accommodationId, Long amenityId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new RuntimeException("Accommodation not found"));
        Amenity amenity = amenityRepository.findById(amenityId)
                .orElseThrow(() -> new RuntimeException("Amenity not found"));

        AccommodationAmenity accommodationAmenity = AccommodationAmenity.builder()
                .accommodation(accommodation)
                .amenity(amenity)
                .build();

        return accommodationAmenityRepository.save(accommodationAmenity).getId();
    }

    public Long updateAccommodationAmenity(Long accommodationAmenityId, Long newAccommodationId, Long newAmenityId) {
        AccommodationAmenity accommodationAmenity = accommodationAmenityRepository.findById(accommodationAmenityId)
                .orElseThrow(() -> new RuntimeException("AccommodationAmenity not found"));

        Accommodation newAccommodation = accommodationRepository.findById(newAccommodationId)
                .orElseThrow(() -> new RuntimeException("Accommodation not found"));
        Amenity newAmenity = amenityRepository.findById(newAmenityId)
                .orElseThrow(() -> new RuntimeException("Amenity not found"));

        AccommodationAmenity updated = AccommodationAmenity.builder()
                .id(accommodationAmenityId)
                .accommodation(newAccommodation)
                .amenity(newAmenity)
                .build();

        return accommodationAmenityRepository.save(updated).getId();
    }

    public void deleteAccommodationAmenity(Long accommodationAmenityId) {
        AccommodationAmenity accommodationAmenity = accommodationAmenityRepository.findById(accommodationAmenityId)
                .orElseThrow(() -> new RuntimeException("AccommodationAmenity not found"));

        accommodationAmenityRepository.delete(accommodationAmenity);
    }
}
