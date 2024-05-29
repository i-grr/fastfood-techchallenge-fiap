package br.com.fiap.fastfood.domain.ports.outbound.product;

import br.com.fiap.fastfood.domain.domain.Product;
import br.com.fiap.fastfood.domain.domain.ProductCategory;

import java.util.List;

public interface FindProductsByCategoryAdapterPort {

    List<Product> findProductsByCategory(ProductCategory category);

}
