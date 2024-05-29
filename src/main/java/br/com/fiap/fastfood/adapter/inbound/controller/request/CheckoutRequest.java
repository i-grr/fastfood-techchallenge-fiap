package br.com.fiap.fastfood.adapter.inbound.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CheckoutRequest(
        String cpf,
        @NotEmpty List<CheckoutItem> items
) {

    public record CheckoutItem(
            @NotNull Long productId,
            @Min(1) int quantity
    ) {}

}
