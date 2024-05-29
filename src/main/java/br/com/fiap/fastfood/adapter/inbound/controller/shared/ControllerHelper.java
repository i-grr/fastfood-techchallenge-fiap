package br.com.fiap.fastfood.adapter.inbound.controller.shared;

import br.com.fiap.fastfood.domain.domain.Error;
import org.springframework.http.ResponseEntity;

public class ControllerHelper {

    public static ResponseEntity<?> handleError(Error error) {
        return switch (error.getType()) {
            case UNPROCESSABLE_ENTITY -> ResponseEntity.unprocessableEntity().body(error.getMessage());
            case NOT_FOUND -> ResponseEntity.notFound().build();
            default -> ResponseEntity.status(500).body("Unexpected error occurred");
        };
    }

}
