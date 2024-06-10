package team03.airdnb.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import team03.airdnb.reservation.dto.request.ReservationSaveDto;

import java.net.URI;

@RequestMapping("/reservations")
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Void> createReservation(@RequestBody ReservationSaveDto reservationSaveDto, UriComponentsBuilder uriComponentsBuilder) {
        Reservation createdReservation = reservationService.createReservation(reservationSaveDto);
        URI location = uriComponentsBuilder.path("/reservations/{id}")
                .buildAndExpand(createdReservation.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }
}
