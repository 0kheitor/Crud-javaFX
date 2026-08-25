package com.template.validator;

public class ProjectTypeValidator implements Validator<String>{
    private static final String EXP = ".*\\d.*";
    private final String projectType;

    public ProjectTypeValidator(String projectType) {
        this.projectType = projectType;
    }

    @Override
    public boolean validate() {
        return !projectType.matches(EXP);
    }

    @Override
    public String getMessageError() {
        return "Insira um tipo de projeto válido";
    }

    @Override
    public String getValor() {
        return projectType;
    }
}
