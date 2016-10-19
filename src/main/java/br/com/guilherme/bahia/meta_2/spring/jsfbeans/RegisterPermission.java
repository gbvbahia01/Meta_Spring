/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.meta_2.spring.jsfbeans;

import br.com.guilherme.bahia.meta_2.spring.models.Permission;
import br.com.guilherme.bahia.meta_2.spring.models.User;
import br.com.guilherme.bahia.meta_2.spring.services.AbstractService;
import br.com.guilherme.bahia.meta_2.spring.services.PermissionService;
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
public class RegisterPermission extends Register<Permission> implements Serializable {

    @ManagedProperty("#{perService}")
    private PermissionService perService;

    private Permission permission = new Permission();

    public String register() {
        super.register(permission);
        newPermission();
        return "perm.xhtml";
    }

    public String remove() {
        super.remove(permission);
        newPermission();
        return "perm.xhtml";
    }

    public void newPermission() {
        permission = new Permission();
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

    public List<Permission> getAllPermissions() {
        return perService.findAll();
    }

    public void setPermEdit(Integer user) {
        this.permission = perService.getById(user);
    }

    public Integer getPermEdit() {
        return this.permission.getId();
    }
}
