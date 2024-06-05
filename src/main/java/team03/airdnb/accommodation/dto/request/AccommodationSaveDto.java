package team03.airdnb.accommodation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team03.airdnb.accommodation.Accommodation;
import team03.airdnb.accommodation.Address;
import team03.airdnb.user.User;

@Getter
@AllArgsConstructor
public class AccommodationSaveDto {

    private String name;
    private String profileImg;
    private Address address;
    private Long price;
    private int headcount;
    private int bed_count;
    private int bedroom_count;
    private int bathroom_count;
    private Long host_id;

    public Accommodation toEntity(User host){
        return Accommodation.builder()
                .name(this.name)
                .profileImg(this.profileImg)
                .address(this.address)
                .price(this.price)
                .headCount(this.headcount)
                .bedCount(this.bed_count)
                .bedroomCount(this.bedroom_count)
                .bathroomCount(this.bathroom_count)
                .host(host)
                .build();
    }
}
