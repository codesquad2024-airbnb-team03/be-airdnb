package team03.airdnb.accommodation;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team03.airdnb.AccommodationAmenity.AccommodationAmenity;
import team03.airdnb.AccommodationAmenity.AccommodationAmenityRepository;
import team03.airdnb.accommodation.dto.request.AccommodationSaveDto;
import team03.airdnb.accommodation.dto.response.AccommodationShowDto;
import team03.airdnb.user.User;
import team03.airdnb.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationAmenityRepository accommodationAmenityRepository;
    private final UserRepository userRepository;

    public Accommodation createAccommodation(AccommodationSaveDto accommodationSaveDto) {
        User host = userRepository.findById(accommodationSaveDto.getHost_id()).get();
        return accommodationRepository.save(accommodationSaveDto.toEntity(host));
    }

    public AccommodationShowDto showAccommodationDetail(Long accommodationId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId).get();

        List<AccommodationAmenity> accommodationAmenityList = accommodationAmenityRepository.findByAccommodationId(
                accommodationId);

        List<String> amenities = accommodationAmenityList.stream()
                .map(accommodationAmenity -> accommodationAmenity.getAmenity().getName())
                .collect(Collectors.toList());

        return AccommodationShowDto.of(accommodation,
                20000L,
                amenities,
                5.0,
                accommodation.getReviews().size(),
                accommodation.getReviews());
    }

}
