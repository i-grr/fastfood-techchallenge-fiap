package br.com.fiap.fastfood.adapter.outbound.repository.order;

import br.com.fiap.fastfood.adapter.outbound.repository.customer.CustomerEntity;
import br.com.fiap.fastfood.adapter.outbound.repository.product.ProductEntity;
import br.com.fiap.fastfood.adapter.outbound.repository.shared.BaseEntity;
import br.com.fiap.fastfood.domain.domain.Order;
import br.com.fiap.fastfood.domain.domain.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Deprecated
    public OrderEntity() {}

    public OrderEntity(CustomerEntity customer, List<OrderItemEntity> items, OrderStatus status, BigDecimal total) {
        this.customer = customer;
        this.items = items;
        this.status = status;
        this.total = total;
    }

    public static OrderEntity fromDomain(Order order, CustomerEntity customerEntity, List<ProductEntity> productEntities) {
        List<OrderItemEntity> itemEntities = order.getItems().stream()
                .map(item -> {
                    ProductEntity productEntity = productEntities.stream()
                            .filter(pe -> pe.getId().equals(item.getProduct().getId()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Product not found for id: " + item.getProduct().getId()));
                    return OrderItemEntity.fromDomain(item, productEntity);
                })
                .collect(Collectors.toList());

        return new OrderEntity(
                customerEntity,
                itemEntities,
                order.getStatus(),
                order.getTotal()
        );
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
