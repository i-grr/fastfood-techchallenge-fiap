package br.com.fiap.fastfood.domain.usecase.customer;

import br.com.fiap.fastfood.domain.domain.Customer;
import br.com.fiap.fastfood.domain.ports.inbound.customer.GetCustomerByCpfUseCasePort;
import br.com.fiap.fastfood.domain.ports.outbound.customer.FindCustomerByCpfAdapterPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetCustomerByCpfUseCase implements GetCustomerByCpfUseCasePort {

    private final FindCustomerByCpfAdapterPort findCustomerByCpfAdapterPort;

    public GetCustomerByCpfUseCase(FindCustomerByCpfAdapterPort findCustomerByCpfAdapterPort) {
        this.findCustomerByCpfAdapterPort = findCustomerByCpfAdapterPort;
    }

    @Override
    public Optional<Customer> execute(String document) {
        return findCustomerByCpfAdapterPort.findCustomerByCpf(document);
    }

}
