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
import validi_track_expiration_monitoring.domain.ComplianceOfficer;
import validi_track_expiration_monitoring.service.ComplianceOfficerService;

@RestController
@RequestMapping("/api/compliance-officers")
public class ComplianceOfficerController {
    private static final String NOT_FOUND_MESSAGE = "The item with that id does not exist";
    private final ComplianceOfficerService complianceOfficerService;

    public ComplianceOfficerController(ComplianceOfficerService complianceOfficerService) {
        this.complianceOfficerService = complianceOfficerService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ComplianceOfficer complianceOfficer) {
        complianceOfficerService.save(complianceOfficer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Compliance officer saved successfully");
    }

    @GetMapping("/all")
    public List<ComplianceOfficer> findAll() {
        return complianceOfficerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return complianceOfficerService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody ComplianceOfficer complianceOfficer) {
        if (complianceOfficerService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE);
        }
        complianceOfficer.setId(id);
        return ResponseEntity.ok(complianceOfficerService.update(complianceOfficer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        if (complianceOfficerService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE);
        }
        complianceOfficerService.delete(id);
        return ResponseEntity.ok("Compliance officer deleted successfully");
    }
}
