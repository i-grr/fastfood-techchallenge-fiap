package br.com.fiap.fastfood.domain.domain;

import java.util.Optional;

public class Result<T> {
    private final T value;
    private final Error error;

    private Result(T value, Error error) {
        this.value = value;
        this.error = error;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, null);
    }

    public static <T> Result<T> failure(Error error) {
        return new Result<>(null, error);
    }

    public boolean isSuccess() {
        return error == null;
    }

    public Optional<T> getValue() {
        return Optional.ofNullable(value);
    }

    public Optional<Error> getError() {
        return Optional.ofNullable(error);
    }

}
