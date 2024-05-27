package br.com.fiap.fastfood.domain.domain;

public class Error {

    private final ErrorType type;
    private final String message;

    private Error(ErrorType type, String message) {
        this.type = type;
        this.message = message;
    }

    public static Error createNotFoundError(String message) {
        return new Error(ErrorType.NOT_FOUND, message);
    }

    public static Error createUnprocessableEntityError(String message) {
        return new Error(ErrorType.UNPROCESSABLE_ENTITY, message);
    }

    public ErrorType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public enum ErrorType {
        NOT_FOUND,
        UNPROCESSABLE_ENTITY,
    }

}
