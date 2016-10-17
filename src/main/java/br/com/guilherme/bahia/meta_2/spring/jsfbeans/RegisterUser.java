/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.meta_2.spring.jsfbeans;

import br.com.guilherme.bahia.meta_2.spring.models.Department;
import br.com.guilherme.bahia.meta_2.spring.models.User;
import br.com.guilherme.bahia.meta_2.spring.services.DepartmentService;
import br.com.guilherme.bahia.meta_2.spring.services.UserService;
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
public class RegisterUser {

    @ManagedProperty("#{userService}")
    private UserService userService;
    @ManagedProperty("#{depService}")
    private DepartmentService depService;

    private User user = new User();

    public String register() {
        userService.register(user);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("The User " + this.user.getName() + " Is Registered Successfully"));
        user = new User();
        return "";
    }

    public List<Department> getAllDeparts(){
        return depService.findAll();
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
}
