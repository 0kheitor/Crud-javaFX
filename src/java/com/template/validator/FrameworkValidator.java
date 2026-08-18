package com.template.validator;

import com.template.util.DialogUtil;

public class FrameworkValidator {
    public static boolean validateFramework(String name, String tecnology, String highestVersion, String projectType){
        if(name.isEmpty() || tecnology.isEmpty() || highestVersion.isEmpty() || projectType.isEmpty()){
            DialogUtil.showWarning("Some field is empty.");
            return false;
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
