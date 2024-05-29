package br.com.fiap.fastfood.adapter.outbound.repository.product;

import br.com.fiap.fastfood.domain.ports.outbound.product.ExistsProductByIdAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class ExistsProductByIdAdapter implements ExistsProductByIdAdapterPort {

    private final ProductRepository productRepository;

    public ExistsProductByIdAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean existsProductById(Long id) {
        return productRepository.existsByIdAndIsDeleted(id, false);
    }
}
