package team03.airdnb.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team03.airdnb.accommodation.Accommodation;
import team03.airdnb.accommodation.AccommodationRepository;
import team03.airdnb.accommodation.AccommodationService;
import team03.airdnb.review.dto.request.ReviewSaveDto;
import team03.airdnb.user.User;
import team03.airdnb.user.UserRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;
    private final AccommodationService accommodationService;

    public Review createReview(ReviewSaveDto reviewSaveDto) {
        User user = userRepository.findById(reviewSaveDto.getUserId()).get(); // 예외처리 추가할 예정입니다!
        Accommodation accommodation = accommodationRepository.findById(reviewSaveDto.getAccommodationId()).get(); // 예외처리 추가할 예정입니다!

        // accommodation averageGrade 업데이트
        accommodationService.updateAverageGradeOnReviewAdd(reviewSaveDto.getAccommodationId(), reviewSaveDto.getGrade());

        return reviewRepository.save(reviewSaveDto.toEntity(user, accommodation));
    }
}
