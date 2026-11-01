package validi_track_expiration_monitoring.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import validi_track_expiration_monitoring.domain.Item;
import validi_track_expiration_monitoring.domain.ValidityStatus;
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

    public List<Item> findByStatus(ValidityStatus status) {
        if (status == null) {
            return Collections.emptyList();
        }
        return itemRepository.findByStatus(status);
    }

    public List<Item> findByExpiryDateBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            return Collections.emptyList();
        }
        return itemRepository.findByExpiryDateBetween(startDate, endDate);
    }

    public List<Item> findByComplianceOfficerIdAndExpiryDate(UUID complianceOfficerId, LocalDate expiryDate) {
        if (complianceOfficerId == null || expiryDate == null) {
            return Collections.emptyList();
        }
        return itemRepository.findByComplianceOfficerIdAndExpiryDate(complianceOfficerId, expiryDate);
    }

    public List<Item> findItemsByBranchId(UUID branchId) {
        if (branchId == null) {
            return Collections.emptyList();
        }
        return itemRepository.findItemsByBranchId(branchId);
    }

    public List<Item> findExpiredItemsByOfficer(UUID officerId) {
        if (officerId == null) {
            return Collections.emptyList();
        }
        return itemRepository.findExpiredItemsByOfficer(officerId);
    }

    public long countItemsByStatus(ValidityStatus status) {
        if (status == null) {
            return 0L;
        }
        return itemRepository.countItemsByStatus(status);
    }

    public Item update(Item item) {
        return itemRepository.save(item);
    }

    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }
}
