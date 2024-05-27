package br.com.fiap.fastfood.domain.ports.outbound.customer;

import br.com.fiap.fastfood.domain.domain.Customer;

import java.util.Optional;

public interface FindCustomerByCpfAdapterPort {

    Optional<Customer> findCustomerByCpf(String cpf);

}
