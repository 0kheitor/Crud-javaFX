package com.template;

import java.util.Scanner;

public class FrameworkDTO {

    private int id;
    private String name;
    private String tecnology;
    private String projectType;
    private String highestVersion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTecnology() {
        return tecnology;
    }

    public void setTecnology(String tecnology) {
        this.tecnology = tecnology;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projecttType) {
        this.projectType = projecttType;
    }

    public String getHighestVersion() {
        return highestVersion;
    }

    public void setHighestVersion(String highestVersion) {
        this.highestVersion = highestVersion;
    }
}