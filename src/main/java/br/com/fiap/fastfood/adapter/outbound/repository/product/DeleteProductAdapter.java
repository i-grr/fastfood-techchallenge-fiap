package br.com.fiap.fastfood.adapter.outbound.repository.product;

import br.com.fiap.fastfood.domain.domain.Product;
import br.com.fiap.fastfood.domain.ports.outbound.product.DeleteProductAdapterPort;
import br.com.fiap.fastfood.domain.ports.outbound.product.SaveProductAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductAdapter implements DeleteProductAdapterPort {

    private final ProductRepository productRepository;

    public DeleteProductAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean delete(Long id) {
        var productExists = productRepository.existsByIdAndIsDeleted(id, false);

        if (!productExists) {
            return false;
        }

        var optionalEntity = productRepository.findById(id);

        optionalEntity.ifPresent(entityToPersist -> {
            entityToPersist.setDeleted(true);
            productRepository.save(entityToPersist);
        });

        return true;
    }

}
