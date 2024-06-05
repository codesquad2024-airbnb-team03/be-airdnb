package team03.airdnb.accommodation;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import team03.airdnb.accommodation.dto.request.AccommodationSaveDto;
import team03.airdnb.accommodation.dto.response.AccommodationShowDto;

@RequestMapping("/accommodations")
@RestController
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;

    @PostMapping
    public ResponseEntity<Void> createAccommodation(@RequestBody AccommodationSaveDto accommodationSaveDto, UriComponentsBuilder uriComponentsBuilder) {
        Accommodation createdAccommodation = accommodationService.createAccommodation(accommodationSaveDto);
        URI location = uriComponentsBuilder.path("/accommodations/{id}")
                .buildAndExpand(createdAccommodation.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccommodationShowDto> showAccommodationDetail(@PathVariable("id") Long accommodationId) {
        AccommodationShowDto accommodationShowDto = accommodationService.showAccommodationDetail(accommodationId);

        return ResponseEntity.ok(accommodationShowDto);
    }

}
