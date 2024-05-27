package br.com.fiap.fastfood.domain.ports.outbound.customer;

import br.com.fiap.fastfood.domain.domain.Customer;

public interface SaveCustomerAdapterPort {

    Customer save(Customer customer);

}
