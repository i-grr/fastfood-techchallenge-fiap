package br.com.fiap.fastfood.adapter.outbound.repository.product;

import br.com.fiap.fastfood.domain.domain.Product;
import br.com.fiap.fastfood.domain.domain.ProductCategory;
import br.com.fiap.fastfood.domain.domain.Result;
import br.com.fiap.fastfood.domain.ports.outbound.product.FindProductsByCategoryAdapterPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class FindProductsByCategoryAdapter implements FindProductsByCategoryAdapterPort {

    private final ProductRepository productRepository;

    public FindProductsByCategoryAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findProductsByCategory(ProductCategory category) {
        var entityProducts = productRepository.findAllByCategoryAndIsDeleted(category, false);
        return entityProducts.stream()
                .map(Product::fromEntity)
                .filter(Result::isSuccess)
                .map(result -> result.getValue().orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }
}
