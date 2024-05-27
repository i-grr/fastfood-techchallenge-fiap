package br.com.fiap.fastfood.domain.ports.inbound.customer;

import br.com.fiap.fastfood.domain.domain.Customer;

import java.util.Optional;

public interface GetCustomerByCpfUseCasePort {

    Optional<Customer> execute(String document);

}
