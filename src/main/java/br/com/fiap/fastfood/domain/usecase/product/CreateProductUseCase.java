package br.com.fiap.fastfood.domain.usecase.product;

import br.com.fiap.fastfood.domain.domain.Product;
import br.com.fiap.fastfood.domain.domain.Result;
import br.com.fiap.fastfood.domain.ports.inbound.product.CreateProductUseCasePort;
import br.com.fiap.fastfood.domain.ports.outbound.product.SaveProductAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class CreateProductUseCase implements CreateProductUseCasePort {

    private final SaveProductAdapterPort saveProduct;

    public CreateProductUseCase(SaveProductAdapterPort saveProductAdapter) {
        this.saveProduct = saveProductAdapter;
    }

    @Override
    public Result<Product> execute(Product product) {
        return Result.success(saveProduct.save(product));
    }

}
