package br.com.fiap.fastfood.domain.usecase.product;

import br.com.fiap.fastfood.domain.ports.inbound.product.DeleteProductUseCasePort;
import br.com.fiap.fastfood.domain.ports.outbound.product.DeleteProductAdapterPort;
import br.com.fiap.fastfood.domain.ports.outbound.product.ExistsProductByIdAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductUseCase implements DeleteProductUseCasePort {

    private final DeleteProductAdapterPort deleteProduct;
    private final ExistsProductByIdAdapterPort existsProductById;

    public DeleteProductUseCase(DeleteProductAdapterPort deleteProduct, ExistsProductByIdAdapterPort existsProductById) {
        this.deleteProduct = deleteProduct;
        this.existsProductById = existsProductById;
    }

    @Override
    public boolean execute(Long id) {
        var productAlreadyExists = existsProductById.existsProductById(id);

        if (!productAlreadyExists) {
            return false;
        }

        return deleteProduct.delete(id);
    }
}
