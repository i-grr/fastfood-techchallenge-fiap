package br.com.fiap.fastfood.domain.usecase.product;

import br.com.fiap.fastfood.domain.domain.Error;
import br.com.fiap.fastfood.domain.domain.Product;
import br.com.fiap.fastfood.domain.domain.ProductCategory;
import br.com.fiap.fastfood.domain.domain.Result;
import br.com.fiap.fastfood.domain.ports.inbound.product.CreateProductUseCasePort;
import br.com.fiap.fastfood.domain.ports.inbound.product.GetProductsByCategoryUseCasePort;
import br.com.fiap.fastfood.domain.ports.outbound.product.FindProductsByCategoryAdapterPort;
import br.com.fiap.fastfood.domain.ports.outbound.product.SaveProductAdapterPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetProductsByCategoryUseCase implements GetProductsByCategoryUseCasePort {

    private final FindProductsByCategoryAdapterPort findProductsByCategory;

    public GetProductsByCategoryUseCase(FindProductsByCategoryAdapterPort findProductsByCategory) {
        this.findProductsByCategory = findProductsByCategory;
    }


    @Override
    public Result<List<Product>> execute(String category) {
        var productCategory = ProductCategory.from(category);

        if (productCategory == null) {
            return Result.failure(Error.createUnprocessableEntityError("Category is not exists"));
        }

        return Result.success(findProductsByCategory.findProductsByCategory(productCategory));
    }
}
