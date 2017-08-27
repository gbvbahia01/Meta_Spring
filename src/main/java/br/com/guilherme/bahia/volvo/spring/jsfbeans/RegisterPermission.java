/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.volvo.spring.jsfbeans;

import br.com.guilherme.bahia.volvo.spring.models.Permission;
import br.com.guilherme.bahia.volvo.spring.repositories.AbstractRepositories;
import br.com.guilherme.bahia.volvo.spring.repositories.PermissionRepositories;
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
    private PermissionRepositories perService;

    private Permission permission = new Permission();

    public String register() {
        try {
            super.register(permission);
        } catch (IllegalArgumentException e) {
            errorMsg(e);
        }
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
    public AbstractRepositories<Permission> getService() {
        return getPerService();
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public PermissionRepositories getPerService() {
        return perService;
    }

    public void setPerService(PermissionRepositories perService) {
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
