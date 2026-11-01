package validi_track_expiration_monitoring.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import validi_track_expiration_monitoring.domain.Item;
import validi_track_expiration_monitoring.repository.ItemRepository;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Optional<Item> findById(UUID id) {
        return itemRepository.findById(id);
    }

    public Item update(Item item) {
        return itemRepository.save(item);
    }

    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }
}
