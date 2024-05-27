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

    private final SaveCustomerAdapterPort saveCustomerAdapterPort;
    private final ExistsCustomerByDocumentAdapterPort existsCustomerByDocumentAdapterPort;

    public CreateCustomerUseCase(SaveCustomerAdapterPort saveCustomerAdapterPort, ExistsCustomerByDocumentAdapterPort existsCustomerByDocumentAdapterPort) {
        this.saveCustomerAdapterPort = saveCustomerAdapterPort;
        this.existsCustomerByDocumentAdapterPort = existsCustomerByDocumentAdapterPort;
    }

    @Override
    public Result<Customer> execute(Customer customer) {
        var customerAlreadyExists = existsCustomerByDocumentAdapterPort.existsCustomerByDocument(customer.getDocument(), customer.getDocumentType());

        if (customerAlreadyExists) {
            return Result.failure(Error.createUnprocessableEntityError("The provided document is already in use."));
        }

        return Result.success(saveCustomerAdapterPort.save(customer));
    }

}
