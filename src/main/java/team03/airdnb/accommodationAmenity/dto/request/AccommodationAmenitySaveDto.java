package team03.airdnb.accommodationAmenity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccommodationAmenitySaveDto {
    private Long accommodationId;
    private Long amenityId;
}
