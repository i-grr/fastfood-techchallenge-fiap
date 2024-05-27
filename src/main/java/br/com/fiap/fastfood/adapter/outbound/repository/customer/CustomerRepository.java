package br.com.fiap.fastfood.adapter.outbound.repository.customer;

import br.com.fiap.fastfood.domain.domain.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsByDocumentAndDocumentType(String document, DocumentType documentType);

}
