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
import validi_track_expiration_monitoring.domain.Item;
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
    public ResponseEntity<String> save(@RequestBody Item item) {
        itemService.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body("Item saved successfully");
    }

    @GetMapping("/all")
    public List<Item> findAll() {
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return itemService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody Item item) {
        if (itemService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE);
        }
        item.setId(id);
        return ResponseEntity.ok(itemService.update(item));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        if (itemService.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND_MESSAGE);
        }
        itemService.delete(id);
        return ResponseEntity.ok("Item deleted successfully");
    }
}
