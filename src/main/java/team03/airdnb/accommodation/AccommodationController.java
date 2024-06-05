package team03.airdnb.accommodation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import team03.airdnb.accommodation.dto.request.AccommodationSaveDto;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;

    @PostMapping("/accommodations")
    public ResponseEntity<Void> createAccommodation(@RequestBody AccommodationSaveDto accommodationSaveDto, UriComponentsBuilder uriComponentsBuilder) {
        Accommodation createdAccommodation = accommodationService.createAccommodation(accommodationSaveDto);
        URI location = uriComponentsBuilder.path("/accommodations/{id}")
                .buildAndExpand(createdAccommodation.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }
}
