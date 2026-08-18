package com.template.service;

import com.template.model.dao.FrameworkDAO;
import com.template.model.dto.FrameworkDTO;

import java.util.ArrayList;

public class FrameworkService {

    private final FrameworkDAO frameworkDao = new FrameworkDAO();

    public void save(FrameworkDTO framework) {
        frameworkDao.postFramework(framework);
    }

    public void update(FrameworkDTO framework) {
        frameworkDao.updateFramework(framework);
    }

    public void delete(int id) {
        frameworkDao.deleteFramework(id);
    }

    public ArrayList<FrameworkDTO> getAll() {
        return frameworkDao.getAllFrameworks();
    }
}