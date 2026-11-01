package validi_track_expiration_monitoring.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import validi_track_expiration_monitoring.domain.ComplianceOfficer;
import validi_track_expiration_monitoring.repository.ComplianceOfficerRepository;

@Service
public class ComplianceOfficerService {
    private final ComplianceOfficerRepository complianceOfficerRepository;

    public ComplianceOfficerService(ComplianceOfficerRepository complianceOfficerRepository) {
        this.complianceOfficerRepository = complianceOfficerRepository;
    }

    public ComplianceOfficer save(ComplianceOfficer complianceOfficer) {
        return complianceOfficerRepository.save(complianceOfficer);
    }

    public List<ComplianceOfficer> findAll() {
        return complianceOfficerRepository.findAll();
    }

    public Optional<ComplianceOfficer> findById(UUID id) {
        return complianceOfficerRepository.findById(id);
    }

    public ComplianceOfficer update(ComplianceOfficer complianceOfficer) {
        return complianceOfficerRepository.save(complianceOfficer);
    }

    public void delete(UUID id) {
        complianceOfficerRepository.deleteById(id);
    }
}
