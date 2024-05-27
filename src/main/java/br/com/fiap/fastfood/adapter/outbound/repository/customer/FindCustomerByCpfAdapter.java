package br.com.fiap.fastfood.adapter.outbound.repository.customer;

import br.com.fiap.fastfood.domain.domain.Customer;
import br.com.fiap.fastfood.domain.domain.DocumentType;
import br.com.fiap.fastfood.domain.ports.outbound.customer.FindCustomerByCpfAdapterPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindCustomerByCpfAdapter implements FindCustomerByCpfAdapterPort {

    private final CustomerRepository customerRepository;

    public FindCustomerByCpfAdapter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> findCustomerByCpf(String cpf) {
        return customerRepository.findByDocumentAndDocumentType(cpf, DocumentType.CPF)
                .flatMap(entity -> Customer.fromEntity(entity).getValue());
    }

}
