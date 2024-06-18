package team03.airdnb.accommodationAmenity;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team03.airdnb.accommodationAmenity.dto.request.AccommodationAmenityUpdateDto;

@RequestMapping("/accommodationAmenity")
@RestController
@RequiredArgsConstructor
public class AccommodationAmenityController {

    private final AccommodationAmenityService accommodationAmenityService;

//    @PostMapping
//    public ResponseEntity<Void> createAccommodationAmenity(@RequestBody AccommodationAmenitySaveDto saveDto) {
//        Long createdId = accommodationAmenityService.createAccommodationAmenity(saveDto);
//
//        return ResponseEntity
//                .noContent()
//                .build();
//    }

    @PutMapping
    public ResponseEntity<Void> updateAccommodationAmenity(@RequestBody AccommodationAmenityUpdateDto updateDto) {

        accommodationAmenityService.updateAccommodationAmenity(updateDto);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{accommodationAmenityId}")
    public ResponseEntity<Void> deleteAccommodationAmenity(@PathVariable Long accommodationAmenityId) {

        accommodationAmenityService.deleteAccommodationAmenity(accommodationAmenityId);

        return ResponseEntity
                .noContent()
                .build();
    }
}
