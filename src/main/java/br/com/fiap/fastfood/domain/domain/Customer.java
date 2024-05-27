package br.com.fiap.fastfood.domain.domain;

import br.com.fiap.fastfood.adapter.outbound.repository.customer.CustomerEntity;

import java.util.regex.Pattern;

public class Customer {

    private Long id;
    private String name;
    private String document;
    private DocumentType documentType;
    private String email;
    private boolean isIdentified;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    private Customer(Long id, String name, String document, DocumentType documentType, String email, boolean isIdentified) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.documentType = documentType;
        this.email = email;
        this.isIdentified = isIdentified;
    }

    private Customer(String name, String document, DocumentType documentType, String email, boolean isIdentified) {
        this.name = name;
        this.document = document;
        this.documentType = documentType;
        this.email = email;
        this.isIdentified = isIdentified;
    }

    public static Result<Customer> createIdentifiedCustomer(String name, String document, String email) {
        if (!validateName(name)) {
            return Result.failure(Error.createUnprocessableEntityError("Name must not be null and must have at most 80 characters."));
        }

        if (!validateDocument(document)) {
            return Result.failure(Error.createUnprocessableEntityError("Document must be exactly 11 digits."));
        }

        if (!validateEmail(email)) {
            return Result.failure(Error.createUnprocessableEntityError("Invalid email format."));
        }

        return Result.success(new Customer(name, document, DocumentType.CPF, email, true));
    }

    public static Result<Customer> createNotIdentifiedCustomer() {
        return Result.success(new Customer(null, null, null, null, false));
    }

    public static Result<Customer> fromEntity(CustomerEntity entity) {
        return Result.success(new Customer(
                entity.getId(),
                entity.getName(),
                entity.getDocument(),
                entity.getDocumentType(),
                entity.getEmail(),
                entity.isIdentified()
        ));
    }

    public Long getId() {
        return id;
    }

    public String getDocument() {
        return document;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    private static boolean validateName(String name) {
        return name != null && name.length() <= 80;
    }

    private static boolean validateDocument(String document) {
        return document != null && document.length() == 11 && document.matches("\\d+");
    }

    private static boolean validateEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

}
