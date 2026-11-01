package validi_track_expiration_monitoring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;
import java.util.UUID;

@Entity
public class DocumentType {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "documentTypes")
    private List<ComplianceOfficer> complianceOfficers;

    public DocumentType() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ComplianceOfficer> getComplianceOfficers() {
        return complianceOfficers;
    }

    public void setComplianceOfficers(List<ComplianceOfficer> complianceOfficers) {
        this.complianceOfficers = complianceOfficers;
    }
}
