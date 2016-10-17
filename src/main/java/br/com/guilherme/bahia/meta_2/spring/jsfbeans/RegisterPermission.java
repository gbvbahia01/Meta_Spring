/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.meta_2.spring.jsfbeans;

import br.com.guilherme.bahia.meta_2.spring.models.Department;
import br.com.guilherme.bahia.meta_2.spring.models.Permission;
import br.com.guilherme.bahia.meta_2.spring.services.AbstractService;
import br.com.guilherme.bahia.meta_2.spring.services.PermissionService;
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
public class RegisterPermission extends Register<Permission>{

    @ManagedProperty("#{perService}")
    private PermissionService perService;

    private Permission permission = new Permission();

    public String register() {
        super.register(permission);
        permission = new Permission();
        return "perm.xhtml";
    }
    
    @Override
    public AbstractService<Permission> getService() {
        return getPerService();
    }
    
    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public PermissionService getPerService() {
        return perService;
    }

    public void setPerService(PermissionService perService) {
        this.perService = perService;
    }

}
