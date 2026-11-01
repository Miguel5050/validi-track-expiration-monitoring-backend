package validi_track_expiration_monitoring.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import validi_track_expiration_monitoring.domain.DocumentType;
import validi_track_expiration_monitoring.service.DocumentTypeService;

@RestController
@RequestMapping("/api/document-types")
public class DocumentTypeController {
    private static final String NOT_FOUND_MESSAGE = "The item with that id does not exist";
    private final DocumentTypeService documentTypeService;

    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody DocumentType documentType) {
        documentTypeService.save(documentType);
        return ResponseEntity.status(HttpStatus.CREATED).body("Document type saved successfully");
    }

    @GetMapping("/all")
    public List<DocumentType> findAll() {
        return documentTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return documentTypeService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody DocumentType documentType) {
        if (documentTypeService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE);
        }
        documentType.setId(id);
        return ResponseEntity.ok(documentTypeService.update(documentType));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        if (documentTypeService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE);
        }
        documentTypeService.delete(id);
        return ResponseEntity.ok("Document type deleted successfully");
    }
}
