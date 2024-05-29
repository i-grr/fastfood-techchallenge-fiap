package br.com.fiap.fastfood.domain.ports.outbound.order;

import br.com.fiap.fastfood.domain.domain.Order;

public interface SaveOrderAdapterPort {

    Order save(Order order);

}
