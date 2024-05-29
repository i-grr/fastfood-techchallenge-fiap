package br.com.fiap.fastfood.domain.ports.inbound.order;

import br.com.fiap.fastfood.domain.domain.Order;

import java.util.List;

public interface GetOrdersUseCasePort {

    List<Order> execute();

}
