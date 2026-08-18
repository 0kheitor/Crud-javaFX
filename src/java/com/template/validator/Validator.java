package com.template.validator;

public interface Validator<T> {
    boolean validate();
    String getMessageError();
    T getValor();
}
