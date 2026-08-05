package io.github.stock_control_api.validate;

public interface Validator<T> {
    void shouldExists(T entity);
    void shouldNotExists(T entity);
    void checkUpdate(T newEntity, T oldEntity);
}
