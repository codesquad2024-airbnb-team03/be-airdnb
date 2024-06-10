package team03.airdnb.user;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team03.airdnb.accommodation.dto.response.AccommodationListDto;
import team03.airdnb.review.dto.response.ReviewShowDto;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}/favorites")
    public ResponseEntity<List<AccommodationListDto>> showFavoriteList(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.showFavoriteList(userId));
    }

    @GetMapping("/{userId}/reviews")
    public ResponseEntity<List<ReviewShowDto>> showReviewList(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.showReviewList(userId));
    }
}
