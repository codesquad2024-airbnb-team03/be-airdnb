package team03.airdnb.accommodationAmenity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.airdnb.accommodation.Accommodation;
import team03.airdnb.accommodation.AccommodationRepository;
import team03.airdnb.amenity.Amenity;
import team03.airdnb.amenity.AmenityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AccommodationAmenityService {

    private final AccommodationAmenityRepository accommodationAmenityRepository;
    private final AccommodationRepository accommodationRepository;
    private final AmenityRepository amenityRepository;

    public void createAccommodationAmenity(List<Long> amenityIds, Long accommodationId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId).get();
        List<AccommodationAmenity> accommodationAmenities = amenityIds.stream()
                .map(amenityId -> {
                    Amenity amenity = amenityRepository.findById(amenityId).get();
                    return AccommodationAmenity.builder()
                            .accommodation(accommodation)
                            .amenity(amenity)
                            .build();
                })
                .collect(Collectors.toList());

        accommodationAmenityRepository.saveAll(accommodationAmenities);
    }

    public void updateAccommodationAmenity(List<Long> amenityIds, Long accommodationId) {
        accommodationAmenityRepository.deleteByAccommodationId(accommodationId);

        Accommodation accommodation = accommodationRepository.findById(accommodationId).get();
        List<AccommodationAmenity> accommodationAmenities = amenityIds.stream()
                .map(amenityId -> {
                    Amenity amenity = amenityRepository.findById(amenityId).get();
                    return AccommodationAmenity.builder()
                            .accommodation(accommodation)
                            .amenity(amenity)
                            .build();
                })
                .collect(Collectors.toList());

        accommodationAmenityRepository.saveAll(accommodationAmenities);
    }

//    public Long createAccommodationAmenity(AccommodationAmenitySaveDto saveDto) {
//        Accommodation accommodation = accommodationRepository.findById(saveDto.getAccommodationId())
//                .orElseThrow(() -> new RuntimeException("Accommodation not found"));
//        Amenity amenity = amenityRepository.findById(saveDto.getAmenityId())
//                .orElseThrow(() -> new RuntimeException("Amenity not found"));
//
//        AccommodationAmenity accommodationAmenity = AccommodationAmenity.builder()
//                .accommodation(accommodation)
//                .amenity(amenity)
//                .build();
//
//        return accommodationAmenityRepository.save(accommodationAmenity).getId();
//    }
//
//    public void updateAccommodationAmenity(AccommodationAmenityUpdateDto updateDto) {
//        accommodationAmenityRepository.findById(updateDto.getId())
//                .orElseThrow(() -> new RuntimeException("AccommodationAmenity not found"));
//
//        Accommodation newAccommodation = accommodationRepository.findById(updateDto.getAccommodationId())
//                .orElseThrow(() -> new RuntimeException("Accommodation not found"));
//        Amenity newAmenity = amenityRepository.findById(updateDto.getAmenityId())
//                .orElseThrow(() -> new RuntimeException("Amenity not found"));
//
//        AccommodationAmenity updated = AccommodationAmenity.builder()
//                .id(updateDto.getId())
//                .accommodation(newAccommodation)
//                .amenity(newAmenity)
//                .build();
//
//        accommodationAmenityRepository.save(updated);
//    }
//
//    public void deleteAccommodationAmenity(Long accommodationAmenityId) {
//        AccommodationAmenity accommodationAmenity = accommodationAmenityRepository.findById(accommodationAmenityId)
//                .orElseThrow(() -> new RuntimeException("AccommodationAmenity not found"));
//
//        accommodationAmenityRepository.delete(accommodationAmenity);
//    }
}
