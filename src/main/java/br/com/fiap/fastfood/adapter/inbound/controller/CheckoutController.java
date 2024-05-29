package br.com.fiap.fastfood.adapter.inbound.controller;

import br.com.fiap.fastfood.adapter.inbound.controller.request.CheckoutRequest;
import br.com.fiap.fastfood.adapter.inbound.controller.response.CheckoutResponse;
import br.com.fiap.fastfood.adapter.inbound.controller.shared.ControllerHelper;
import br.com.fiap.fastfood.domain.domain.Error;
import br.com.fiap.fastfood.domain.domain.*;
import br.com.fiap.fastfood.domain.ports.inbound.customer.GetCustomerByCpfUseCasePort;
import br.com.fiap.fastfood.domain.ports.inbound.order.CreateOrderUseCasePort;
import br.com.fiap.fastfood.domain.ports.inbound.product.GetProductByIdUseCasePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/checkouts")
public class CheckoutController {

    private final CreateOrderUseCasePort createOrderUseCase;
    private final GetCustomerByCpfUseCasePort getCustomerByCpfUseCase;
    private final GetProductByIdUseCasePort getProductByIdUseCase;

    public CheckoutController(CreateOrderUseCasePort createOrderUseCase, GetCustomerByCpfUseCasePort getCustomerByCpfUseCase, GetProductByIdUseCasePort getProductByIdUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.getCustomerByCpfUseCase = getCustomerByCpfUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<?> checkout(@RequestBody @Valid CheckoutRequest request) {
        var optCustomer = getCustomerByCpfUseCase.execute(request.cpf());

        if (optCustomer.isEmpty()) {
            return ControllerHelper.handleError(Error.createNotFoundError("Customer not found"));
        }

        Customer customer = optCustomer.get();

        List<OrderItem> items = request.items().stream()
                .map(item -> {
                    var resultProduct = getProductByIdUseCase.execute(item.productId());
                    if (resultProduct.isSuccess()) {
                        Product product = resultProduct.getValue().orElse(null);
                        if (product != null) {
                            return OrderItem.createOrderItem(product, item.quantity());
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (items.size() != request.items().size()) {
            return ControllerHelper.handleError(Error.createNotFoundError("One or more products not found"));
        }

        var resultOrder = Order.createOrder(customer, items);
        if (!resultOrder.isSuccess()) {
            return ControllerHelper.handleError(resultOrder.getError().get());
        }

        var createdOrder = resultOrder.getValue().orElseThrow();
        var resultOrderUseCase = createOrderUseCase.execute(createdOrder);

        if (!resultOrderUseCase.isSuccess()) {
            return ControllerHelper.handleError(resultOrder.getError().get());
        }

        return ResponseEntity.ok(new CheckoutResponse(resultOrderUseCase.getValue().get().getStatus()));
    }

}
