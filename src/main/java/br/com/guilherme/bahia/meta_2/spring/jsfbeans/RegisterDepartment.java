/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.meta_2.spring.jsfbeans;

import br.com.guilherme.bahia.meta_2.spring.models.Department;
import br.com.guilherme.bahia.meta_2.spring.services.AbstractService;
import br.com.guilherme.bahia.meta_2.spring.services.DepartmentService;
import com.sun.media.sound.SoftEnvelopeGenerator;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Guilherme
 */
@ManagedBean
@ViewScoped
public class RegisterDepartment extends Register<Department> implements Serializable {

    @ManagedProperty("#{depService}")
    private DepartmentService depService;

    private Department dpt = new Department();

    public DepartmentService getDepService() {
        return depService;
    }

    public void setDepService(DepartmentService depService) {
        this.depService = depService;
    }

    public Department getDepartment() {
        return dpt;
    }

    public void setUser(Department dpt) {
        this.dpt = dpt;
    }

    public String register() {
        try {
            super.register(dpt);
        } catch (IllegalArgumentException e) {
            errorMsg(e);
        }
        newDepartment();
        return "dept.xhtml";
    }

    public String remove() {
        super.remove(dpt);
        newDepartment();
        return "dept.xhtml";
    }

    public void newDepartment() {
        dpt = new Department();
    }

    @Override
    public AbstractService<Department> getService() {
        return getDepService();
    }

    public List<Department> getAllDepartments() {
        return depService.findAll();
    }

    public void setDepEdit(Integer dep) {
        this.dpt = depService.getById(dep);
    }

    public Integer getDepEdit() {
        return this.dpt.getId();
    }
}
