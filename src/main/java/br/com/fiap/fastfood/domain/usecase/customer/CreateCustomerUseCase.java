package br.com.fiap.fastfood.domain.usecase.customer;

import br.com.fiap.fastfood.domain.domain.Customer;
import br.com.fiap.fastfood.domain.domain.Error;
import br.com.fiap.fastfood.domain.domain.Result;
import br.com.fiap.fastfood.domain.ports.inbound.customer.CreateCustomerUseCasePort;
import br.com.fiap.fastfood.domain.ports.outbound.customer.ExistsCustomerByDocumentAdapterPort;
import br.com.fiap.fastfood.domain.ports.outbound.customer.SaveCustomerAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomerUseCase implements CreateCustomerUseCasePort {

    private final SaveCustomerAdapterPort saveCustomer;
    private final ExistsCustomerByDocumentAdapterPort existsCustomerByDocument;

    public CreateCustomerUseCase(SaveCustomerAdapterPort saveCustomer, ExistsCustomerByDocumentAdapterPort existsCustomerByDocument) {
        this.saveCustomer = saveCustomer;
        this.existsCustomerByDocument = existsCustomerByDocument;
    }

    @Override
    public Result<Customer> execute(Customer customer) {
        var customerAlreadyExists = existsCustomerByDocument.existsCustomerByDocument(customer.getDocument(), customer.getDocumentType());

        if (customerAlreadyExists) {
            return Result.failure(Error.createUnprocessableEntityError("The provided document is already in use."));
        }

        return Result.success(saveCustomer.save(customer));
    }

}
