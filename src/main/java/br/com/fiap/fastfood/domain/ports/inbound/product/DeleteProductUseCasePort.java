package br.com.fiap.fastfood.domain.ports.inbound.product;

public interface DeleteProductUseCasePort {

    boolean execute(Long id);

}
