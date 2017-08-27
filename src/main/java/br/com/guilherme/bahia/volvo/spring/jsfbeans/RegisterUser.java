/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.volvo.spring.jsfbeans;

import br.com.guilherme.bahia.volvo.spring.models.Department;
import br.com.guilherme.bahia.volvo.spring.models.Permission;
import br.com.guilherme.bahia.volvo.spring.models.User;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import br.com.guilherme.bahia.volvo.spring.repositories.DepartmentRepositorie;
import br.com.guilherme.bahia.volvo.spring.repositories.PermissionRepositorie;
import br.com.guilherme.bahia.volvo.spring.repositories.UserRepositorie;

/**
 *
 * @author Guilherme
 */
@ManagedBean
@ViewScoped
public class RegisterUser extends Register<User, UserRepositorie> implements Serializable {

    @ManagedProperty("#{userRepositorie}")
    private UserRepositorie userRep;
    @ManagedProperty("#{departmentRepositorie}")
    private DepartmentRepositorie depRep;
    @ManagedProperty("#{permissionRepositorie}")
    private PermissionRepositorie perRep;

    private User user = new User();

    public String register() {
        try {
            super.register(user);
        } catch (IllegalArgumentException e) {
            errorMsg(e);
        }
        newUser();
        return "user.xhtml";
    }

    public String remove() {
        super.remove(user);
        newUser();
        return "user.xhtml";
    }

    public void newUser() {
        user = new User();
    }

    public List<Department> getAllDeparts() {
        return depRep.findAll();
    }

    public List<Permission> getAllPermissions() {
        return perRep.findAll();
    }

    public List<User> getAllUsers() {
        return userRep.findAll();
    }

    public DepartmentRepositorie getDepRep() {
        return depRep;
    }

    public void setDepRep(DepartmentRepositorie depRep) {
        this.depRep = depRep;
    }

    public UserRepositorie getUserRep() {
        return userRep;
    }

    public void setUserRep(UserRepositorie userRep) {
        this.userRep = userRep;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PermissionRepositorie getPerRep() {
        return perRep;
    }

    public void setPerRep(PermissionRepositorie perRep) {
        this.perRep = perRep;
    }

    public void setUserEdit(Integer user) {
        this.user = userRep.findOne(user);
    }

    public Integer getUserEdit() {
        return this.user.getId();
    }

    @Override
    public UserRepositorie getRepository() {
       return userRep;
    }

}
