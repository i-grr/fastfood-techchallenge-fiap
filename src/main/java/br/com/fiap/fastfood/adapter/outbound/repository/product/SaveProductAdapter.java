package br.com.fiap.fastfood.adapter.outbound.repository.product;

import br.com.fiap.fastfood.domain.domain.Product;
import br.com.fiap.fastfood.domain.ports.outbound.product.SaveProductAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class SaveProductAdapter implements SaveProductAdapterPort {

    private final ProductRepository productRepository;

    public SaveProductAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        var entityToPersit = ProductEntity.fromDomain(product);
        var persistedEntity = productRepository.save(entityToPersit);
        return Product.fromEntity(persistedEntity).getValue().get();
    }

}
