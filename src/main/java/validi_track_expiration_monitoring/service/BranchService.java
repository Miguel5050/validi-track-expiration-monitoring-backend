package validi_track_expiration_monitoring.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import validi_track_expiration_monitoring.domain.Branch;
import validi_track_expiration_monitoring.repository.BranchRepository;

@Service
public class BranchService {
    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Branch save(Branch branch) {
        return branchRepository.save(branch);
    }

    public List<Branch> findAll() {
        return branchRepository.findAll();
    }

    public Optional<Branch> findById(UUID id) {
        return branchRepository.findById(id);
    }

    public Branch update(Branch branch) {
        return branchRepository.save(branch);
    }

    public void delete(UUID id) {
        branchRepository.deleteById(id);
    }
}
