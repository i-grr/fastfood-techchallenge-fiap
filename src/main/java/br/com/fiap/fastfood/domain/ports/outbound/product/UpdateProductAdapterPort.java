package br.com.fiap.fastfood.domain.ports.outbound.product;

import br.com.fiap.fastfood.domain.domain.Product;

public interface UpdateProductAdapterPort {

    Product update(Long id, Product product);

}
