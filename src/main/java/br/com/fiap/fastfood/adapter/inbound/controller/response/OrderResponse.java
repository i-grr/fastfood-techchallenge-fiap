package br.com.fiap.fastfood.adapter.inbound.controller.response;

import br.com.fiap.fastfood.domain.domain.Customer;
import br.com.fiap.fastfood.domain.domain.Order;
import br.com.fiap.fastfood.domain.domain.OrderItem;
import br.com.fiap.fastfood.domain.domain.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(
        Long id,
        CustomerResponse customer,
        List<OrderItemResponse> items,
        OrderStatus status,
        BigDecimal total
) {

    public static OrderResponse fromDomain(Order order) {
        return new OrderResponse(
                order.getId(),
                CustomerResponse.fromDomain(order.getCustomer()),
                order.getItems().stream().map(OrderItemResponse::fromDomain).toList(),
                order.getStatus(),
                order.getTotal()
        );
    }

    public record OrderItemResponse(
            ProductResponse product,
            int quantity
    ) {
        public static OrderItemResponse fromDomain(OrderItem orderItem) {
            return new OrderItemResponse(
                    ProductResponse.fromDomain(orderItem.getProduct()),
                    orderItem.getQuantity()
            );
        }
    }

    public record CustomerResponse(
            Long id,
            String name,
            String email
    ) {
        public static CustomerResponse fromDomain(Customer customer) {
            return new CustomerResponse(
                    customer.getId(),
                    customer.getName(),
                    customer.getEmail()
            );
        }
    }
}
