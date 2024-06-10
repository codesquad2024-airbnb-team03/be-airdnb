package team03.airdnb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team03.airdnb.accommodation.Accommodation;
import team03.airdnb.accommodation.dto.response.AccommodationListDto;
import team03.airdnb.favorite.Favorite;
import team03.airdnb.reservation.Reservation;
import team03.airdnb.reservation.dto.response.ReservationShowDto;
import team03.airdnb.review.Review;
import team03.airdnb.review.dto.response.ReviewShowDto;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<ReservationShowDto> showReservations(Long userId) {
        User user = userRepository.findById(userId).get();
        List<Reservation> reservations = user.getReservations();

        List<ReservationShowDto> reservationShowDtos = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationShowDtos.add(ReservationShowDto.of(reservation));
        }

        return reservationShowDtos;
    }

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
