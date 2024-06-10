package team03.airdnb.user;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team03.airdnb.accommodation.Accommodation;
import team03.airdnb.accommodation.dto.response.AccommodationListDto;
import team03.airdnb.favorite.Favorite;
import team03.airdnb.review.Review;
import team03.airdnb.review.dto.response.ReviewShowDto;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<AccommodationListDto> showFavoriteList(Long userId) {
        User user = userRepository.findById(userId).get();
        List<Favorite> favorites = user.getFavorites();

        List<AccommodationListDto> accommodationListDtoList = new ArrayList<>();

        for (Favorite favorite : favorites) {
            Accommodation accommodation = favorite.getAccommodation();
            AccommodationListDto accommodationListDto = AccommodationListDto.of(accommodation, accommodation.getAccommodationAmenities());
            accommodationListDtoList.add(accommodationListDto);
        }

        return accommodationListDtoList;
    }

    public List<ReviewShowDto> showReviewList(Long userId) {
        User user = userRepository.findById(userId).get();
        List<Review> reviews = user.getReviews();

        List<ReviewShowDto> reviewShowDtoList = new ArrayList<>();

        for (Review review: reviews) {
            ReviewShowDto reviewShowDto = ReviewShowDto.of(review);
            reviewShowDtoList.add(reviewShowDto);
        }

        return reviewShowDtoList;
    }
}
