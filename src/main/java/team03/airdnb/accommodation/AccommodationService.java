package team03.airdnb.accommodation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.airdnb.accommodation.dto.request.AccommodationFilterDto;
import team03.airdnb.accommodation.dto.request.AccommodationSaveDto;
import team03.airdnb.accommodation.dto.request.AccommodationUpdateDto;
import team03.airdnb.accommodation.dto.response.AccommodationListDto;
import team03.airdnb.accommodation.dto.response.AccommodationShowDto;
import team03.airdnb.accommodationAmenity.AccommodationAmenityService;
import team03.airdnb.amenity.AmenityRepository;
import team03.airdnb.user.User;
import team03.airdnb.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;
    private final AmenityRepository amenityRepository;
    private final AccommodationAmenityService accommodationAmenityService;

    public Long createAccommodation(AccommodationSaveDto accommodationSaveDto) {
        User host = userRepository.findById(accommodationSaveDto.getHostId()).get(); // 예외처리 추가할 예정입니다!
        Long createdAccommodationId = accommodationRepository.save(accommodationSaveDto.toEntity(host)).getId();

        accommodationAmenityService.createAccommodationAmenity(accommodationSaveDto.getAmenityIds(), createdAccommodationId);

        return createdAccommodationId;
    }

    public List<AccommodationListDto> showAccommodationList() {
        List<Accommodation> accommodationList = accommodationRepository.findAll();

        return accommodationList.stream()
                .map(accommodation -> AccommodationListDto.of(accommodation, accommodation.getAccommodationAmenities()))
                .collect(Collectors.toList());
    }

    public AccommodationShowDto showAccommodationDetail(Long accommodationId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId).get(); // 예외처리 추가할 예정입니다!

        // 수수료(fee) 관련해서는 아직 구현하지 못해 임의의 숫자를 넣었습니다.
        return AccommodationShowDto.of(accommodation, 10000L, accommodation.getAccommodationAmenities());
    }

    public void updateAccommodation(AccommodationUpdateDto accommodationUpdateDto) {
        Accommodation accommodation = accommodationRepository.findById(accommodationUpdateDto.getId()).get();
        User host = userRepository.findById(accommodationUpdateDto.getHostId()).get(); // 예외처리 추가할 예정입니다!

        accommodationRepository.save(accommodationUpdateDto.toEntity(host, accommodation.getAverageGrade()));
        accommodationAmenityService.updateAccommodationAmenity(accommodationUpdateDto.getAmenityIds(), accommodationUpdateDto.getId());
    }

    public void deleteAccommodation(Long accommodationId) {
        accommodationRepository.deleteById(accommodationId);
    }

    // Review 추가시 Accommodation averageGrade 업데이트
    public void updateAverageGradeOnReviewAdd(Long accommodationId, double addedGrade) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId).get();
        double previousAverageGrade = accommodation.getAverageGrade();
        int reviewCount = accommodation.getReviews().size();

        double updatedAverageGrade = (previousAverageGrade * reviewCount + addedGrade) / (reviewCount + 1);
        accommodationRepository.updateAverageGrade(accommodationId, updatedAverageGrade);
    }

    // Review 업데이트시 Accommodation averageGrade 업데이트
    public void updateAverageGradeOnReviewUpdate(Long accommodationId, double gradeDifference) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId).get();
        double previousAverageGrade = accommodation.getAverageGrade();
        int reviewCount = accommodation.getReviews().size();

        double updatedAverageGrade = (previousAverageGrade * reviewCount + gradeDifference) / reviewCount;
        accommodationRepository.updateAverageGrade(accommodationId, updatedAverageGrade);
    }

    // Review 삭제시 Accommodation averageGrade 업데이트
    public void updateAverageGradeOnReviewDelete(Long accommodationId, double deletedGrade) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId).get();
        double previousAverageGrade = accommodation.getAverageGrade();
        int reviewCount = accommodation.getReviews().size() + 1; // 삭제 전 개수

        double updatedAverageGrade = (previousAverageGrade * reviewCount - deletedGrade) / (reviewCount - 1);
        accommodationRepository.updateAverageGrade(accommodationId, updatedAverageGrade);
    }

    public List<AccommodationListDto> findAccommodationsByFilters(AccommodationFilterDto filterDto) {
        List<Accommodation> accommodationsByFilters = accommodationRepository.findAccommodationsByFilters(filterDto);
        return accommodationsByFilters.stream()
                .map(accommodation -> AccommodationListDto.of(accommodation, accommodation.getAccommodationAmenities()))
                .collect(Collectors.toList());
    }
}