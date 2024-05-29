package br.com.fiap.fastfood.domain.ports.inbound.product;

import br.com.fiap.fastfood.domain.domain.Product;
import br.com.fiap.fastfood.domain.domain.Result;

import java.util.List;

public interface GetProductsByCategoryUseCasePort {

    Result<List<Product>> execute(String category);

}
