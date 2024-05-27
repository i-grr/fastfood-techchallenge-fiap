package br.com.fiap.fastfood.adapter.outbound.repository.customer;

import br.com.fiap.fastfood.domain.domain.DocumentType;
import br.com.fiap.fastfood.domain.ports.outbound.customer.ExistsCustomerByDocumentAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class ExistsCustomerByDocumentAdapter implements ExistsCustomerByDocumentAdapterPort {

    private final CustomerRepository customerRepository;

    public ExistsCustomerByDocumentAdapter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean existsCustomerByDocument(String document, DocumentType documentType) {
        return customerRepository.existsByDocumentAndDocumentType(document, documentType);
    }

}
