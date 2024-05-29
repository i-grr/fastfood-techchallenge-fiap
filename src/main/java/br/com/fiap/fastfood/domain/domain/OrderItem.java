package br.com.fiap.fastfood.domain.domain;

import br.com.fiap.fastfood.adapter.outbound.repository.order.OrderItemEntity;

public class OrderItem {

    private Long id;

    private Product product;

    private int quantity;

    private OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public static OrderItem createOrderItem(Product product, int quantity) {
        return new OrderItem(
                product,
                quantity
        );
    }

    public static OrderItem fromEntity(OrderItemEntity orderItemEntity) {
        Product product = Product.fromEntity(orderItemEntity.getProduct()).getValue().get();
        return new OrderItem(
                product,
                orderItemEntity.getQuantity()
        );
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

}
