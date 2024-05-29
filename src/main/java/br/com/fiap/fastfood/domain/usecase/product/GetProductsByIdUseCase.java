package br.com.fiap.fastfood.domain.usecase.product;

import br.com.fiap.fastfood.domain.domain.Error;
import br.com.fiap.fastfood.domain.domain.Product;
import br.com.fiap.fastfood.domain.domain.Result;
import br.com.fiap.fastfood.domain.ports.inbound.product.GetProductByIdUseCasePort;
import br.com.fiap.fastfood.domain.ports.outbound.product.FindProductsByIdAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class GetProductsByIdUseCase implements GetProductByIdUseCasePort {

    private final FindProductsByIdAdapterPort findProductsById;

    public GetProductsByIdUseCase(FindProductsByIdAdapterPort findProductsById) {
        this.findProductsById = findProductsById;
    }

    @Override
    public Result<Product> execute(Long id) {
        var optProduct = findProductsById.findProductById(id);

        if (optProduct.isEmpty()) {
            return Result.failure(Error.createNotFoundError("Product with id: " + id + " not found"));
        }

        return Result.success(optProduct.get());
    }
}
