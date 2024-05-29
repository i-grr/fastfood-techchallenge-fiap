package br.com.fiap.fastfood.domain.usecase.product;

import br.com.fiap.fastfood.domain.domain.Error;
import br.com.fiap.fastfood.domain.domain.Product;
import br.com.fiap.fastfood.domain.domain.Result;
import br.com.fiap.fastfood.domain.ports.inbound.product.CreateProductUseCasePort;
import br.com.fiap.fastfood.domain.ports.inbound.product.UpdateProductUseCasePort;
import br.com.fiap.fastfood.domain.ports.outbound.product.ExistsProductByIdAdapterPort;
import br.com.fiap.fastfood.domain.ports.outbound.product.SaveProductAdapterPort;
import br.com.fiap.fastfood.domain.ports.outbound.product.UpdateProductAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductUseCase implements UpdateProductUseCasePort {

    private final UpdateProductAdapterPort updateProduct;
    private final ExistsProductByIdAdapterPort existsProductById;

    public UpdateProductUseCase(UpdateProductAdapterPort updateProduct, ExistsProductByIdAdapterPort existsProductById) {
        this.updateProduct = updateProduct;
        this.existsProductById = existsProductById;
    }

    @Override
    public Result<Product> execute(Long id, Product product) {
        var productAlreadyExists = existsProductById.existsProductById(id);

        if (!productAlreadyExists) {
            return Result.failure(Error.createNotFoundError("Product not found"));
        }

        return Result.success(updateProduct.update(id, product));
    }
}
