package br.com.fiap.fastfood.adapter.inbound.controller.request;

import br.com.fiap.fastfood.domain.domain.Product;
import br.com.fiap.fastfood.domain.domain.ProductCategory;
import br.com.fiap.fastfood.domain.domain.Result;

import java.math.BigDecimal;

public record UpdateProductRequest(
        Long id,
        String name,
        ProductCategory category,
        String description,
        BigDecimal price,
        long preparationTimeSeconds
) {

    public Result<Product> toProduct() {
        return Product.createProduct(name, category, description, price, preparationTimeSeconds);
    }

}
