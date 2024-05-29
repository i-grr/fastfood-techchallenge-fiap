package br.com.fiap.fastfood.domain.usecase.order;

import br.com.fiap.fastfood.domain.domain.Order;
import br.com.fiap.fastfood.domain.domain.Result;
import br.com.fiap.fastfood.domain.ports.inbound.order.CreateOrderUseCasePort;
import br.com.fiap.fastfood.domain.ports.outbound.order.SaveOrderAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class GetOrdersUseCase implements CreateOrderUseCasePort {

    private final SaveOrderAdapterPort saveOrder;

    public GetOrdersUseCase(SaveOrderAdapterPort saveOrder) {
        this.saveOrder = saveOrder;
    }

    @Override
    public Result<Order> execute(Order order) {
        return Result.success(saveOrder.save(order));
    }
}
