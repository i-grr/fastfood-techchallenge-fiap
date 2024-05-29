package br.com.fiap.fastfood.adapter.outbound.repository.product;

import br.com.fiap.fastfood.domain.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByIdAndIsDeleted(Long id, boolean isDeleted);

    Optional<ProductEntity> findByIdAndIsDeleted(Long id, boolean isDeleted);

    List<ProductEntity> findAllByCategoryAndIsDeleted(ProductCategory category, boolean isDeleted);

}
