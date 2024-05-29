package br.com.fiap.fastfood.domain.domain;

import br.com.fiap.fastfood.adapter.outbound.repository.product.ProductEntity;

import java.math.BigDecimal;

public class Product {

    private Long id;

    private String name;

    private ProductCategory category;

    private String description;

    private BigDecimal price;

    private long preparationTimeSeconds;

    private Product(Long id, String name, ProductCategory category, String description, BigDecimal price, long preparationTimeSeconds) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.preparationTimeSeconds = preparationTimeSeconds;
    }

    private Product(String name, ProductCategory category, String description, BigDecimal price, long preparationTimeSeconds) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.preparationTimeSeconds = preparationTimeSeconds;
    }

    public static Result<Product> createProduct(String name, ProductCategory category, String description, BigDecimal price, long preparationTimeSeconds) {
        if (name == null || name.trim().isEmpty()) {
            return Result.failure(Error.createUnprocessableEntityError("Name must not be empty"));
        }

        if (category == null) {
            return Result.failure(Error.createUnprocessableEntityError("Category must not be null"));
        }

        if (description == null || description.trim().isEmpty()) {
            return Result.failure(Error.createUnprocessableEntityError("Description must not be empty"));
        }

        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            return Result.failure(Error.createUnprocessableEntityError("Price must be positive"));
        }

        if (preparationTimeSeconds < 0) {
            return Result.failure(Error.createUnprocessableEntityError("Preparation time must not be negative"));
        }

        return Result.success(new Product(
                name,
                category,
                description,
                price,
                preparationTimeSeconds
        ));
    }

    public static Result<Product> fromEntity(ProductEntity entity) {
        return Result.success(new Product(
                entity.getId(),
                entity.getName(),
                entity.getCategory(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getPreparationTimeSeconds()
        ));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public long getPreparationTimeSeconds() {
        return preparationTimeSeconds;
    }

}
