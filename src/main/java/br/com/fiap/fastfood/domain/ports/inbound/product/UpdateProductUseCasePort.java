package br.com.fiap.fastfood.domain.ports.inbound.product;

import br.com.fiap.fastfood.domain.domain.Product;
import br.com.fiap.fastfood.domain.domain.Result;

public interface UpdateProductUseCasePort {

    Result<Product> execute(Long id, Product product);

}
