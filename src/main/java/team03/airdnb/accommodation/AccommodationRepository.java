package team03.airdnb.accommodation;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Accommodation a SET a.averageGrade = :averageGrade WHERE a.id = :id")
    int updateAverageGrade(@Param("id") Long id, @Param("averageGrade") double averageGrade);
}
