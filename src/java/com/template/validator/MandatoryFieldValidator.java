package com.template.validator;

public class MandatoryFieldValidator  implements  Validator<String>{
    private final String fieldName;
    private final String value;

    public MandatoryFieldValidator(String fieldName, String value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public boolean validate() {
        return this.value != null && !this.value.trim().isEmpty();
    }

    @Override
    public String getMessageError() {
        return "O campo " + fieldName + " deve ser preenchido";
    }

    @Override
    public String getValor() {
        return value;
    }
}
