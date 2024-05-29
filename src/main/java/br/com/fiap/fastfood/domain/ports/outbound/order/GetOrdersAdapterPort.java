package br.com.fiap.fastfood.domain.ports.outbound.order;

import br.com.fiap.fastfood.domain.domain.Order;

import java.util.List;

public interface GetOrdersAdapterPort {

    List<Order> findAllOrders();

}
