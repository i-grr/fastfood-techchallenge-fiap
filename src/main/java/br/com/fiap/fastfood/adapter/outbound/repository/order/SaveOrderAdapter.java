package br.com.fiap.fastfood.adapter.outbound.repository.order;

import br.com.fiap.fastfood.adapter.outbound.repository.customer.CustomerRepository;
import br.com.fiap.fastfood.adapter.outbound.repository.product.ProductRepository;
import br.com.fiap.fastfood.domain.domain.Order;
import br.com.fiap.fastfood.domain.ports.outbound.order.SaveOrderAdapterPort;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SaveOrderAdapter implements SaveOrderAdapterPort {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public SaveOrderAdapter(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order save(Order order) {
        var customerEntity = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        var productEntities = order.getItems().stream()
                .map(item -> productRepository.findById(item.getProduct().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Product not found for id: " + item.getProduct().getId())))
                .collect(Collectors.toList());

        OrderEntity orderEntity = OrderEntity.fromDomain(order, customerEntity, productEntities);
        orderEntity.getItems().forEach(item -> item.setOrder(orderEntity));

        orderRepository.save(orderEntity);

        return Order.fromEntity(orderEntity);
    }

}
