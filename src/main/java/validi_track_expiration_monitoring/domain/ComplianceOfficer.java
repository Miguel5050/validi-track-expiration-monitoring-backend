package validi_track_expiration_monitoring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;
import java.util.UUID;

@Entity
public class ComplianceOfficer {
    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToMany
    @JoinTable(
            name = "compliance_officer_document_type",
            joinColumns = @JoinColumn(name = "compliance_officer_id"),
            inverseJoinColumns = @JoinColumn(name = "document_type_id"))
    private List<DocumentType> documentTypes;

    public ComplianceOfficer() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<DocumentType> getDocumentTypes() {
        return documentTypes;
    }

    public void setDocumentTypes(List<DocumentType> documentTypes) {
        this.documentTypes = documentTypes;
    }
}
