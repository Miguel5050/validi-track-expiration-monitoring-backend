package validi_track_expiration_monitoring.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import validi_track_expiration_monitoring.domain.Holder;
import validi_track_expiration_monitoring.repository.HolderRepository;

@Service
public class HolderService {
    private final HolderRepository holderRepository;

    public HolderService(HolderRepository holderRepository) {
        this.holderRepository = holderRepository;
    }

    public Holder save(Holder holder) {
        return holderRepository.save(holder);
    }

    public List<Holder> findAll() {
        return holderRepository.findAll();
    }

    public Optional<Holder> findById(UUID id) {
        return holderRepository.findById(id);
    }

    public Holder update(Holder holder) {
        return holderRepository.save(holder);
    }

    public void delete(UUID id) {
        holderRepository.deleteById(id);
    }
}
