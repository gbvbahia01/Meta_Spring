/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.volvo.spring.jsfbeans;

import br.com.guilherme.bahia.volvo.spring.models.Permission;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import br.com.guilherme.bahia.volvo.spring.repositories.PermissionRepositorie;

/**
 *
 * @author Guilherme
 */
@ManagedBean
@ViewScoped
public class RegisterPermission extends Register<Permission, PermissionRepositorie> implements Serializable {

    @ManagedProperty("#{permissionRepositorie}")
    private PermissionRepositorie perRep;

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

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public PermissionRepositorie getPerRep() {
        return perRep;
    }

    public void setPerRep(PermissionRepositorie perRep) {
        this.perRep = perRep;
    }

    public List<Permission> getAllPermissions() {
        return perRep.findAll();
    }

    public void setPermEdit(Integer user) {
        this.permission = perRep.findOne(user);
    }

    public Integer getPermEdit() {
        return this.permission.getId();
    }

    @Override
    public PermissionRepositorie getRepository() {
        return this.perRep;
    }
}
