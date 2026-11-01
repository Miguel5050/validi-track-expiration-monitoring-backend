package validi_track_expiration_monitoring.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import validi_track_expiration_monitoring.domain.Holder;

public interface HolderRepository extends JpaRepository<Holder, UUID> {
}
