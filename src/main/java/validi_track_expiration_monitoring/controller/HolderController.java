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
import validi_track_expiration_monitoring.domain.Holder;
import validi_track_expiration_monitoring.service.HolderService;

@RestController
@RequestMapping("/api/holders")
public class HolderController {
    private static final String NOT_FOUND_MESSAGE = "The item with that id does not exist";
    private final HolderService holderService;

    public HolderController(HolderService holderService) {
        this.holderService = holderService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Holder holder) {
        holderService.save(holder);
        return ResponseEntity.status(HttpStatus.CREATED).body("Holder saved successfully");
    }

    @GetMapping("/all")
    public List<Holder> findAll() {
        return holderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return holderService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Holder holder) {
        if (holderService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE);
        }
        holder.setId(id);
        return ResponseEntity.ok(holderService.update(holder));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        if (holderService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE);
        }
        holderService.delete(id);
        return ResponseEntity.ok("Holder deleted successfully");
    }
}
