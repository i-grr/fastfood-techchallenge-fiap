package br.com.fiap.fastfood.adapter.inbound.controller.response;

import br.com.fiap.fastfood.domain.domain.Product;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String category,
        String description,
        BigDecimal price,
        long preparationTimeSeconds
) {

    public static ProductResponse fromDomain(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getCategory().name(),
                product.getDescription(),
                product.getPrice(),
                product.getPreparationTimeSeconds()
        );
    }

}
