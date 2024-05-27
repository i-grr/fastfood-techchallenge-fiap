package br.com.fiap.fastfood.domain.ports.inbound.customer;

import br.com.fiap.fastfood.domain.domain.Customer;
import br.com.fiap.fastfood.domain.domain.Result;

public interface CreateCustomerUseCasePort {

    Result<Customer> execute(Customer customer);

}
