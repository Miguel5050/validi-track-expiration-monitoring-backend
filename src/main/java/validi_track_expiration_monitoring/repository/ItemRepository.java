package validi_track_expiration_monitoring.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import validi_track_expiration_monitoring.domain.Item;
import validi_track_expiration_monitoring.domain.ValidityStatus;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findByStatus(ValidityStatus status);

    List<Item> findByExpiryDateBetween(LocalDate startDate, LocalDate endDate);

    List<Item> findByComplianceOfficerIdAndExpiryDate(UUID complianceOfficerId, LocalDate expiryDate);

    @Query("SELECT i FROM Item i WHERE i.holder.branch.id = :branchId")
    List<Item> findItemsByBranchId(@Param("branchId") UUID branchId);

    @Query("SELECT i FROM Item i WHERE i.status = 'EXPIRED' AND i.complianceOfficer.id = :officerId")
    List<Item> findExpiredItemsByOfficer(@Param("officerId") UUID officerId);

    @Query("SELECT COUNT(i) FROM Item i WHERE i.status = :status")
    long countItemsByStatus(@Param("status") ValidityStatus status);
}
