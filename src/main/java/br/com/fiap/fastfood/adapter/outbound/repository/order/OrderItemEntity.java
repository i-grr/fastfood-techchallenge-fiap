package br.com.fiap.fastfood.adapter.outbound.repository.order;

import br.com.fiap.fastfood.adapter.outbound.repository.product.ProductEntity;
import br.com.fiap.fastfood.adapter.outbound.repository.shared.BaseEntity;
import br.com.fiap.fastfood.domain.domain.OrderItem;
import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Deprecated
    public OrderItemEntity() {}

    public OrderItemEntity(OrderEntity order, ProductEntity product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public static OrderItemEntity fromDomain(OrderItem orderItem, ProductEntity productEntity) {
        return new OrderItemEntity(
                null,
                productEntity,
                orderItem.getQuantity()
        );
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}