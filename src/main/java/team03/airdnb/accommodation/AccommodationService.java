package team03.airdnb.accommodation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team03.airdnb.accommodation.dto.request.AccommodationSaveDto;
import team03.airdnb.user.User;
import team03.airdnb.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;

    public Accommodation createAccommodation(AccommodationSaveDto accommodationSaveDto) {
        User host = userRepository.findById(accommodationSaveDto.getHost_id()).get();
        return accommodationRepository.save(accommodationSaveDto.toEntity(host));
    }
}
