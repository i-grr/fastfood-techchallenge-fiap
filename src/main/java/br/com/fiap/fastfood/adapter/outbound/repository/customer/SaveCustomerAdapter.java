package br.com.fiap.fastfood.adapter.outbound.repository.customer;

import br.com.fiap.fastfood.domain.domain.Customer;
import br.com.fiap.fastfood.domain.ports.outbound.customer.SaveCustomerAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class SaveCustomerAdapter implements SaveCustomerAdapterPort {

    private final CustomerRepository customerRepository;

    public SaveCustomerAdapter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        var entityToPersit = CustomerEntity.fromDomain(customer);
        var persistedEntity = customerRepository.save(entityToPersit);
        return Customer.fromEntity(persistedEntity).getValue().get();
    }

}
