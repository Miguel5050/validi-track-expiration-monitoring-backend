package validi_track_expiration_monitoring.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import validi_track_expiration_monitoring.domain.DocumentType;
import validi_track_expiration_monitoring.repository.DocumentTypeRepository;

@Service
public class DocumentTypeService {
    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeService(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public DocumentType save(DocumentType documentType) {
        return documentTypeRepository.save(documentType);
    }

    public List<DocumentType> findAll() {
        return documentTypeRepository.findAll();
    }

    public Optional<DocumentType> findById(UUID id) {
        return documentTypeRepository.findById(id);
    }

    public DocumentType update(DocumentType documentType) {
        return documentTypeRepository.save(documentType);
    }

    public void delete(UUID id) {
        documentTypeRepository.deleteById(id);
    }
}
