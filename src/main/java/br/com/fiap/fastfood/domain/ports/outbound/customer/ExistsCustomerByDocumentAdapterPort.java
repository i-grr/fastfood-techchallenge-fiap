package br.com.fiap.fastfood.domain.ports.outbound.customer;

import br.com.fiap.fastfood.domain.domain.DocumentType;

public interface ExistsCustomerByDocumentAdapterPort {

    boolean existsCustomerByDocument(String document, DocumentType documentType);

}
