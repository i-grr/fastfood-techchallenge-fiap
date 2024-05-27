package br.com.fiap.fastfood.adapter.inbound.controller.response;

import br.com.fiap.fastfood.domain.domain.Customer;

public record CustomerResponse(
        Long id,
        String name,
        String document,
        String documentType,
        String email
) {

    public static CustomerResponse fromDomain(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getDocument(),
                customer.getDocumentType().name(),
                customer.getEmail()
        );
    }

}
