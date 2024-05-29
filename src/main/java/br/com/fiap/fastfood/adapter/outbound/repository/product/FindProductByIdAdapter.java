package br.com.fiap.fastfood.adapter.outbound.repository.product;

import br.com.fiap.fastfood.domain.domain.Product;
import br.com.fiap.fastfood.domain.domain.Result;
import br.com.fiap.fastfood.domain.ports.outbound.product.FindProductsByIdAdapterPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindProductByIdAdapter implements FindProductsByIdAdapterPort {

    private final ProductRepository productRepository;

    public FindProductByIdAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findByIdAndIsDeleted(id, false)
                .map(Product::fromEntity)
                .flatMap(Result::getValue);
    }

}
