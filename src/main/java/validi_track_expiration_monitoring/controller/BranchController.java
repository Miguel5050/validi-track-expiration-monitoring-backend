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
import validi_track_expiration_monitoring.domain.Branch;
import validi_track_expiration_monitoring.service.BranchService;

@RestController
@RequestMapping("/api/branches")
public class BranchController {
    private static final String NOT_FOUND_MESSAGE = "The item with that id does not exist";
    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Branch branch) {
        branchService.save(branch);
        return ResponseEntity.status(HttpStatus.CREATED).body("Branch saved successfully");
    }

    @GetMapping("/all")
    public List<Branch> findAll() {
        return branchService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return branchService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Branch branch) {
        if (branchService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE);
        }
        branch.setId(id);
        return ResponseEntity.ok(branchService.update(branch));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        if (branchService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE);
        }
        branchService.delete(id);
        return ResponseEntity.ok("Branch deleted successfully");
    }
}
