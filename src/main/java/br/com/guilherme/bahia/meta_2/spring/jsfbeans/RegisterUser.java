/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.meta_2.spring.jsfbeans;

import br.com.guilherme.bahia.meta_2.spring.models.Department;
import br.com.guilherme.bahia.meta_2.spring.models.Permission;
import br.com.guilherme.bahia.meta_2.spring.models.User;
import br.com.guilherme.bahia.meta_2.spring.services.AbstractService;
import br.com.guilherme.bahia.meta_2.spring.services.DepartmentService;
import br.com.guilherme.bahia.meta_2.spring.services.PermissionService;
import br.com.guilherme.bahia.meta_2.spring.services.UserService;
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
public class RegisterUser extends Register<User>  implements Serializable{

    @ManagedProperty("#{userService}")
    private UserService userService;
    @ManagedProperty("#{depService}")
    private DepartmentService depService;
    @ManagedProperty("#{perService}")
    private PermissionService perService;

    private User user = new User();

    public String register() {
        super.register(user);
        user = new User();
        return "user.xhtml";
    }

    @Override
    public AbstractService<User> getService() {
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

    public DepartmentService getDepService() {
        return depService;
    }

    public void setDepService(DepartmentService depService) {
        this.depService = depService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PermissionService getPerService() {
        return perService;
    }

    public void setPerService(PermissionService perService) {
        this.perService = perService;
    }

    public void setUserEdit(Integer user) {
        this.user = userService.getById(user);
    }

    public Integer getUserEdit() {
        return this.user.getId();
    }
}
