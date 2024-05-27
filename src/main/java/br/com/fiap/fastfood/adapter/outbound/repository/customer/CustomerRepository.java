package br.com.fiap.fastfood.adapter.outbound.repository.customer;

import br.com.fiap.fastfood.domain.domain.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsByDocumentAndDocumentType(String document, DocumentType documentType);

    Optional<CustomerEntity> findByDocumentAndDocumentType(String document, DocumentType documentType);

}
