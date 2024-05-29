package br.com.fiap.fastfood.adapter.outbound.repository.product;

import br.com.fiap.fastfood.domain.domain.Product;
import br.com.fiap.fastfood.domain.ports.outbound.product.UpdateProductAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class UpdateProductAdapter implements UpdateProductAdapterPort {

    private final ProductRepository productRepository;

    public UpdateProductAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product update(Long id, Product product) {
        return productRepository.findById(id)
                .map(entityToPersist -> {
                    update(entityToPersist, product);
                    var persistedEntity = productRepository.save(entityToPersist);
                    return Product.fromEntity(persistedEntity).getValue().orElse(null);
                })
                .orElse(null);
    }

    private void update(ProductEntity entity, Product product) {
        entity.setName(product.getName());
        entity.setCategory(product.getCategory());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setPreparationTimeSeconds(product.getPreparationTimeSeconds());
    }

}
