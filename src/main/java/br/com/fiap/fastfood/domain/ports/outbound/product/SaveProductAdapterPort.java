package br.com.fiap.fastfood.domain.ports.outbound.product;

import br.com.fiap.fastfood.domain.domain.Product;

public interface SaveProductAdapterPort {

    Product save(Product product);

}
