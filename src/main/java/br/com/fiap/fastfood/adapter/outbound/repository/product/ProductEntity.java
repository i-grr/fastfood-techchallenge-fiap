package br.com.fiap.fastfood.adapter.outbound.repository.product;

import br.com.fiap.fastfood.adapter.outbound.repository.shared.BaseEntity;
import br.com.fiap.fastfood.domain.domain.Error;
import br.com.fiap.fastfood.domain.domain.Product;
import br.com.fiap.fastfood.domain.domain.ProductCategory;
import br.com.fiap.fastfood.domain.domain.Result;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private ProductCategory category;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "preparation_time_seconds", nullable = false)
    private long preparationTimeSeconds;

    @Deprecated
    public ProductEntity() {}

    public ProductEntity(String name, ProductCategory category, String description, BigDecimal price, long preparationTimeSeconds) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.preparationTimeSeconds = preparationTimeSeconds;
    }

    public static ProductEntity fromDomain(Product product) {
        return new ProductEntity(
                product.getName(),
                product.getCategory(),
                product.getDescription(),
                product.getPrice(),
                product.getPreparationTimeSeconds()
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getPreparationTimeSeconds() {
        return preparationTimeSeconds;
    }

    public void setPreparationTimeSeconds(long preparationTimeSeconds) {
        this.preparationTimeSeconds = preparationTimeSeconds;
    }

}
