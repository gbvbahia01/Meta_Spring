/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.meta_2.spring.rest.to;

import br.com.guilherme.bahia.meta_2.spring.models.Permission;
import br.com.guilherme.bahia.meta_2.spring.models.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public class UserTo implements Serializable {

    public static List<UserTo> toList(List<User> users){
        List<UserTo> toReturn = new ArrayList<>();
        if(users != null){
            for(User u : users){
                toReturn.add(new UserTo(u));
            }
        }
        return toReturn;
    }
    
    private Integer id;
    private String name;
    private String description;
    private List<Integer> idPermissions = new ArrayList<>();
    private Integer idDepartment;

    public UserTo() {
    }

    public UserTo(User user) {
        if (user != null) {
            this.id = user.getId();
            this.name = user.getName();
            this.description = user.getDescription();
            for (Permission per : user.getUserPermissionList()) {
                this.idPermissions.add(per.getId());
            }
            if (user.getDept() != null) {
                this.idDepartment = user.getDept().getId();
            }
        }else{
            description = "User not found!";
            name = "User not found!";
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getIdPermissions() {
        return idPermissions;
    }

    public void setIdPermissions(List<Integer> idPermissions) {
        this.idPermissions = idPermissions;
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

}
