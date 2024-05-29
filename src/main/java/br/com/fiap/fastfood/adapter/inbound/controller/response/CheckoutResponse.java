package br.com.fiap.fastfood.adapter.inbound.controller.response;

import br.com.fiap.fastfood.domain.domain.OrderStatus;

public record CheckoutResponse(
        OrderStatus orderStatus
) {
}
