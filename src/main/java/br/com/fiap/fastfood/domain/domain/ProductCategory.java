package br.com.fiap.fastfood.domain.domain;

import java.util.Arrays;

public enum ProductCategory {
    SNACK,
    SIDE_DISH,
    BEVERAGE,
    DESSERT;

    public static ProductCategory from(String category) {
        if (category == null || category.trim().isEmpty()) {
            return null;
        }

        return Arrays.stream(ProductCategory.values())
                .filter(e -> e.name().equalsIgnoreCase(category.trim()))
                .findFirst()
                .orElse(null);
    }

}

