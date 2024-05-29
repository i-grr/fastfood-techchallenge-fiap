package br.com.fiap.fastfood.adapter.outbound.repository.order;

import br.com.fiap.fastfood.domain.domain.Order;
import br.com.fiap.fastfood.domain.ports.outbound.order.GetOrdersAdapterPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetOrdersAdapter implements GetOrdersAdapterPort {

    private final OrderRepository orderRepository;

    public GetOrdersAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll().stream().map(Order::fromEntity).collect(Collectors.toList());
    }
}
