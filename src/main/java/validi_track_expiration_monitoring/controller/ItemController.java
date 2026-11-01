package validi_track_expiration_monitoring.controller;

import jakarta.validation.Valid;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import validi_track_expiration_monitoring.domain.Item;
import validi_track_expiration_monitoring.domain.ValidityStatus;
import validi_track_expiration_monitoring.exception.ResourceNotFoundException;
import validi_track_expiration_monitoring.service.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private static final String NOT_FOUND_MESSAGE = "The item with that id does not exist";
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@Valid @RequestBody Item item) {
        itemService.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body("Item saved successfully");
    }

    @GetMapping("/all")s
    public List<Item> findAll() {
        return itemService.findAll();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Item>> findByStatus(@PathVariable ValidityStatus status) {
        List<Item> items = itemService.findByStatus(status);
        return items.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(items);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Item>> findByExpiryDateBetween(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return ResponseEntity.badRequest().build();
        }
        List<Item> items = itemService.findByExpiryDateBetween(startDate, endDate);
        return items.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(items);
    }

    @GetMapping("/compliance-officer/{complianceOfficerId}/expiry-date/{expiryDate}")
    public ResponseEntity<List<Item>> findByComplianceOfficerIdAndExpiryDate(
            @PathVariable UUID complianceOfficerId,
            @PathVariable LocalDate expiryDate) {
        List<Item> items = itemService.findByComplianceOfficerIdAndExpiryDate(complianceOfficerId, expiryDate);
        return items.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(items);
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<Item>> findItemsByBranchId(@PathVariable UUID branchId) {
        List<Item> items = itemService.findItemsByBranchId(branchId);
        return items.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(items);
    }

    @GetMapping("/officer/{officerId}/expired")
    public ResponseEntity<List<Item>> findExpiredItemsByOfficer(@PathVariable UUID officerId) {
        List<Item> items = itemService.findExpiredItemsByOfficer(officerId);
        return items.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(items);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countItemsByStatus(@RequestParam ValidityStatus status) {
        if (status == null) {
            return ResponseEntity.badRequest().build();
        }
        long count = itemService.countItemsByStatus(status);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Item item = itemService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MESSAGE));
        return ResponseEntity.ok(item);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @Valid @RequestBody Item item) {
        if (itemService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException(NOT_FOUND_MESSAGE);
        }
        item.setId(id);
        return ResponseEntity.ok(itemService.update(item));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        if (itemService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException(NOT_FOUND_MESSAGE);
        }
        itemService.delete(id);
        return ResponseEntity.ok("Item deleted successfully");
    }
}
