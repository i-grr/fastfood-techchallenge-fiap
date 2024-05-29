package br.com.fiap.fastfood.domain.ports.outbound.product;

import br.com.fiap.fastfood.domain.domain.Product;

import java.util.Optional;

public interface FindProductsByIdAdapterPort {

    Optional<Product> findProductById(Long id);

}
