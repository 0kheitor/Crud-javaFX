package com.template.validator;

public class IdValidator implements  Validator<String>{
    private final String id;

    public IdValidator(String id) {
        this.id = id;
    }

    @Override
    public boolean validate() {
        if (id.isEmpty()) {
            return false;
        }
        try {
            int value = Integer.parseInt(id);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getMessageError() {
        return "Digite um id numérico";
    }

    @Override
    public String getValor() {
        return id;
    }
}
