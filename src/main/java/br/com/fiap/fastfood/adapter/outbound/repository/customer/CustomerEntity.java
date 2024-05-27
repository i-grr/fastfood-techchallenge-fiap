package br.com.fiap.fastfood.adapter.outbound.repository.customer;

import br.com.fiap.fastfood.adapter.outbound.repository.shared.BaseEntity;
import br.com.fiap.fastfood.domain.domain.Customer;
import br.com.fiap.fastfood.domain.domain.DocumentType;
import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "document", nullable = false, length = 11, unique = true)
    private String document;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false)
    private DocumentType documentType;

    @Column(name = "email", nullable = false, length = 64)
    private String email;

    @Column(name = "is_identified", nullable = false)
    private boolean isIdentified;

    @Deprecated
    public CustomerEntity() {}

    public CustomerEntity(String name, String document, DocumentType documentType, String email, boolean isIdentified) {
        this.name = name;
        this.document = document;
        this.documentType = documentType;
        this.email = email;
        this.isIdentified = isIdentified;
    }

    public static CustomerEntity fromDomain(Customer customer) {
        return new CustomerEntity(
                customer.getName(),
                customer.getDocument(),
                customer.getDocumentType(),
                customer.getEmail(),
                customer.isIdentified()
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean isIdentified) {
        this.isIdentified = isIdentified;
    }

}
