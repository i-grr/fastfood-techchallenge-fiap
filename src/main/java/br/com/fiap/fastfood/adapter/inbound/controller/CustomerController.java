package br.com.fiap.fastfood.adapter.inbound.controller;

import br.com.fiap.fastfood.adapter.inbound.controller.request.CreateCustomerRequest;
import br.com.fiap.fastfood.adapter.inbound.controller.response.CustomerResponse;
import br.com.fiap.fastfood.domain.domain.Customer;
import br.com.fiap.fastfood.domain.domain.Error;
import br.com.fiap.fastfood.domain.ports.inbound.customer.CreateCustomerUseCasePort;
import br.com.fiap.fastfood.domain.ports.inbound.customer.GetCustomerByCpfUseCasePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CreateCustomerUseCasePort createCustomerUseCase;
    private final GetCustomerByCpfUseCasePort getCustomerByCpfUseCase;

    public CustomerController(CreateCustomerUseCasePort createCustomerUseCase, GetCustomerByCpfUseCasePort getCustomerByCpfUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.getCustomerByCpfUseCase = getCustomerByCpfUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerRequest request) {
        var resultCustomer = request.toCustomer();

        if (resultCustomer.isSuccess()) {
            Customer customer = resultCustomer.getValue().get();
            var resultCreateCustomer = createCustomerUseCase.execute(customer);

            if (resultCreateCustomer.isSuccess()) {
                Customer createdCustomer = resultCreateCustomer.getValue().get();
                var location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdCustomer.getId())
                        .toUri();

                return ResponseEntity.created(location).build();
            } else {
                return handleError(resultCreateCustomer.getError().get());
            }
        } else {
            return handleError(resultCustomer.getError().get());
        }
    }

    @GetMapping
    public ResponseEntity<?> getCustomer(@RequestParam(required = true, name = "cpf") String cpf) {
        var optCustomer = getCustomerByCpfUseCase.execute(cpf);
        if (optCustomer.isPresent()) {
            return ResponseEntity.ok(CustomerResponse.fromDomain(optCustomer.get()));
        }

        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> handleError(Error error) {
        return switch (error.getType()) {
            case UNPROCESSABLE_ENTITY -> ResponseEntity.unprocessableEntity().body(error.getMessage());
            case NOT_FOUND -> ResponseEntity.notFound().build();
            default -> ResponseEntity.status(500).body("Unexpected error occurred");
        };
    }

}
