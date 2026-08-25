package com.template.validator;

import com.template.model.dto.FrameworkDTO;
import com.template.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

public class FrameworkValidator {
    public static boolean validateFramework(FrameworkDTO frameworkDTO){

        String name = frameworkDTO.getName();
        String tecnology = frameworkDTO.getTecnology();
        String highestVersion = frameworkDTO.getHighestVersion();
        String projectType = frameworkDTO.getProjectType();

        List<Validator<String>> validators = new ArrayList<>();
        validators.add(new MandatoryFieldValidator("name",name));
        validators.add(new MandatoryFieldValidator("tecnology",tecnology));
        validators.add(new MandatoryFieldValidator("highestVersion",highestVersion));
        validators.add(new MandatoryFieldValidator("projectType", projectType));
        validators.add(new ProjectTypeValidator(projectType));

        for(Validator<String> validator: validators){
            if(!validator.validate()){
                DialogUtil.showWarning(validator.getMessageError());
                return false;
            }
        }
        return true;
    }

    public static boolean isValidId(String id) {
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
}
