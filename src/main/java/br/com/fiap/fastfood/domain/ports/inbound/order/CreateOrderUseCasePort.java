package br.com.fiap.fastfood.domain.ports.inbound.order;

import br.com.fiap.fastfood.domain.domain.Order;
import br.com.fiap.fastfood.domain.domain.Result;

public interface CreateOrderUseCasePort {

    Result<Order> execute(Order order);

}
