package validi_track_expiration_monitoring.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import validi_track_expiration_monitoring.domain.ComplianceOfficer;

public interface ComplianceOfficerRepository extends JpaRepository<ComplianceOfficer, UUID> {
}
