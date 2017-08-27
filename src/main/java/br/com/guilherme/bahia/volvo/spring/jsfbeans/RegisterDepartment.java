/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.volvo.spring.jsfbeans;

import br.com.guilherme.bahia.volvo.spring.models.Department;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import br.com.guilherme.bahia.volvo.spring.repositories.DepartmentRepositorie;

/**
 *
 * @author Guilherme
 */
@ManagedBean
@ViewScoped
public class RegisterDepartment extends Register<Department, DepartmentRepositorie> implements Serializable {

    @ManagedProperty("#{departmentRepositorie}")
    private DepartmentRepositorie depRep;

    private Department dpt = new Department();

    @Override
    public DepartmentRepositorie getRepository() {
        return depRep;
    }

    public void setDepRep(DepartmentRepositorie depRep) {
        this.depRep = depRep;
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

    public List<Department> getAllDepartments() {
        return depRep.findAll();
    }

    public void setDepEdit(Integer dep) {
        this.dpt = depRep.findOne(dep);
    }

    public Integer getDepEdit() {
        return this.dpt.getId();
    }
}
