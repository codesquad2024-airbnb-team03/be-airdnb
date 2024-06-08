package team03.airdnb.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import team03.airdnb.review.dto.request.ReviewSaveDto;

import java.net.URI;

@RequestMapping("/reviews")
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Void> createReview(@RequestBody ReviewSaveDto reviewSaveDto, UriComponentsBuilder uriComponentsBuilder) {
        Review createdReview = reviewService.createReview(reviewSaveDto);
        URI location = uriComponentsBuilder.path("/reviews/{id}")
                .buildAndExpand(createdReview.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }
}
