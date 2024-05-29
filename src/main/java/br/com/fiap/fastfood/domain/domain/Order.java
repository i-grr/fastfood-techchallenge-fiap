package br.com.fiap.fastfood.domain.domain;

import br.com.fiap.fastfood.adapter.outbound.repository.order.OrderEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Order {

    private Long id;

    private Customer customer;

    private List<OrderItem> items;

    private OrderStatus status;

    private BigDecimal total;

    private Order(Customer customer, List<OrderItem> items, OrderStatus status, BigDecimal total) {
        this.customer = customer;
        this.items = items;
        this.status = status;
        this.total = total;
    }

    public static Result<Order> createOrder(Customer customer, List<OrderItem> items) {
        if (customer == null) {
            return Result.failure(Error.createUnprocessableEntityError("Customer cannot be null"));
        }

        if (items == null || items.isEmpty()) {
            return Result.failure(Error.createUnprocessableEntityError("Order must contain at least one item"));
        }

        for (var item : items) {
            if (item == null || item.getProduct() == null) {
                return Result.failure(Error.createUnprocessableEntityError("Order item and product cannot be null"));
            }
        }

        var total = items.stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return Result.success(new Order(
                customer,
                items,
                OrderStatus.RECEIVED,
                total
        ));
    }

    public static Order fromEntity(OrderEntity persistedEntity) {
        Customer customer = Customer.fromEntity(persistedEntity.getCustomer()).getValue().get();
        List<OrderItem> items = persistedEntity.getItems().stream()
                .map(OrderItem::fromEntity)
                .collect(Collectors.toList());

        return new Order(
                customer,
                items,
                persistedEntity.getStatus(),
                persistedEntity.getTotal()
        );
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getTotal() {
        return total;
    }

}
