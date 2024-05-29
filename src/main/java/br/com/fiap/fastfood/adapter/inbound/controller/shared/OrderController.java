package br.com.fiap.fastfood.adapter.inbound.controller.shared;

import br.com.fiap.fastfood.adapter.inbound.controller.request.CreateProductRequest;
import br.com.fiap.fastfood.adapter.inbound.controller.request.UpdateProductRequest;
import br.com.fiap.fastfood.adapter.inbound.controller.response.OrderResponse;
import br.com.fiap.fastfood.adapter.inbound.controller.response.ProductResponse;
import br.com.fiap.fastfood.domain.domain.Order;
import br.com.fiap.fastfood.domain.domain.Product;
import br.com.fiap.fastfood.domain.ports.inbound.product.CreateProductUseCasePort;
import br.com.fiap.fastfood.domain.ports.inbound.product.DeleteProductUseCasePort;
import br.com.fiap.fastfood.domain.ports.inbound.product.GetProductsByCategoryUseCasePort;
import br.com.fiap.fastfood.domain.ports.inbound.product.UpdateProductUseCasePort;
import br.com.fiap.fastfood.domain.ports.outbound.order.GetOrdersAdapterPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final GetOrdersAdapterPort getOrders;

    public OrderController(GetOrdersAdapterPort getOrders) {
        this.getOrders = getOrders;
    }

    @GetMapping
    public ResponseEntity<?> getOrders() {
        List<Order> products = getOrders.findAllOrders();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(products.stream().map(OrderResponse::fromDomain).collect(Collectors.toList()));
        }
    }


}
