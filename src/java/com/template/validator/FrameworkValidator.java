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

    public static boolean validateTerm(){
        return true; //
    }
}
