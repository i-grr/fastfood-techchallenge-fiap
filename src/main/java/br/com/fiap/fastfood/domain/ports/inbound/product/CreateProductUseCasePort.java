package br.com.fiap.fastfood.domain.ports.inbound.product;

import br.com.fiap.fastfood.domain.domain.Product;
import br.com.fiap.fastfood.domain.domain.Result;

public interface CreateProductUseCasePort {

    Result<Product> execute(Product product);

}
