package validi_track_expiration_monitoring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private UUID id;

    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private ValidityStatus status;

    private String itemName;
    private String issuer;

    @ManyToOne
    @JoinColumn(name = "holder_id")
    private Holder holder;

    @ManyToOne
    @JoinColumn(name = "compliance_officer_id")
    private ComplianceOfficer complianceOfficer;

    public Item() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public ValidityStatus getStatus() {
        return status;
    }

    public void setStatus(ValidityStatus status) {
        this.status = status;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Holder getHolder() {
        return holder;
    }

    public void setHolder(Holder holder) {
        this.holder = holder;
    }

    public ComplianceOfficer getComplianceOfficer() {
        return complianceOfficer;
    }

    public void setComplianceOfficer(ComplianceOfficer complianceOfficer) {
        this.complianceOfficer = complianceOfficer;
    }
}
