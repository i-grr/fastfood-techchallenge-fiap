package br.com.fiap.fastfood.domain.ports.outbound.product;

public interface ExistsProductByIdAdapterPort {

    boolean existsProductById(Long id);

}
