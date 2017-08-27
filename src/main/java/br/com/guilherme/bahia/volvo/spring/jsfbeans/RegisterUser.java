/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.volvo.spring.jsfbeans;

import br.com.guilherme.bahia.volvo.spring.models.Department;
import br.com.guilherme.bahia.volvo.spring.models.Permission;
import br.com.guilherme.bahia.volvo.spring.models.User;
import br.com.guilherme.bahia.volvo.spring.repositories.AbstractRepositories;
import br.com.guilherme.bahia.volvo.spring.repositories.DepartmentRepositories;
import br.com.guilherme.bahia.volvo.spring.repositories.PermissionRepositories;
import br.com.guilherme.bahia.volvo.spring.repositories.UserRepositories;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme
 */
@ManagedBean
@ViewScoped
public class RegisterUser extends Register<User> implements Serializable {

    @ManagedProperty("#{userRepositories}")
    private UserRepositories userService;
    @ManagedProperty("#{departmentRepositories}")
    private DepartmentRepositories depService;
    @ManagedProperty("#{permissionRepositories}")
    private PermissionRepositories perService;

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

    @Override
    public AbstractRepositories<User> getService() {
        return getUserService();
    }

    public List<Department> getAllDeparts() {
        return depService.findAll();
    }

    public List<Permission> getAllPermissions() {
        return perService.findAll();
    }

    public List<User> getAllUsers() {
        return userService.findAll();
    }

    public DepartmentRepositories getDepService() {
        return depService;
    }

    public void setDepService(DepartmentRepositories depService) {
        this.depService = depService;
    }

    public UserRepositories getUserService() {
        return userService;
    }

    public void setUserService(UserRepositories userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PermissionRepositories getPerService() {
        return perService;
    }

    public void setPerService(PermissionRepositories perService) {
        this.perService = perService;
    }

    public void setUserEdit(Integer user) {
        this.user = userService.getById(user);
    }

    public Integer getUserEdit() {
        return this.user.getId();
    }

}
