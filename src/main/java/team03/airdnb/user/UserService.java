package team03.airdnb.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team03.airdnb.accommodation.Accommodation;
import team03.airdnb.accommodation.dto.response.AccommodationListDto;
import team03.airdnb.favorite.Favorite;
import team03.airdnb.reservation.dto.response.ReservationShowDto;
import team03.airdnb.review.Review;
import team03.airdnb.review.dto.response.ReviewShowDto;
import team03.airdnb.user.dto.request.UserSaveDto;
import team03.airdnb.user.dto.response.UserShowDto;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(UserSaveDto userSaveDto) {
        String encodedPassword = passwordEncoder.encode(userSaveDto.getPassword());
        userSaveDto.setPassword(encodedPassword);
        return userRepository.save(userSaveDto.toEntity());
    }

    public UserShowDto showLoginUser(String name) {
        User loginUser = userRepository.findByName(name).get();
        return UserShowDto.of(loginUser);
    }

    public List<ReservationShowDto> showReservations(Long userId) {
        User user = userRepository.findById(userId).get();

        return user.getReservations().stream()
                .map(ReservationShowDto::of)
                .collect(Collectors.toList());
    }

    public List<AccommodationListDto> showFavorites(Long userId) {
        User user = userRepository.findById(userId).get();
        List<Favorite> favorites = user.getFavorites();

        List<AccommodationListDto> accommodationListDtos = new ArrayList<>();

        for (Favorite favorite : favorites) {
            Accommodation accommodation = favorite.getAccommodation();
            AccommodationListDto accommodationListDto = AccommodationListDto.of(accommodation,
                    accommodation.getAccommodationAmenities());
            accommodationListDtos.add(accommodationListDto);
        }

        return accommodationListDtos;
    }

    public List<ReviewShowDto> showReviews(Long userId) {
        User user = userRepository.findById(userId).get();
        List<Review> reviews = user.getReviews();

        return reviews.stream().map(ReviewShowDto::of).collect(Collectors.toList());
    }
}
