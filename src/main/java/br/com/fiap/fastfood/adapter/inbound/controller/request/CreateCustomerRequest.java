package br.com.fiap.fastfood.adapter.inbound.controller.request;

import br.com.fiap.fastfood.domain.domain.Customer;
import br.com.fiap.fastfood.domain.domain.Result;

public record CreateCustomerRequest(
        String name,
        String cpf,
        String email
) {

    public Result<Customer> toCustomer() {
        return Customer.createIdentifiedCustomer(name, cpf, email);
    }

}
