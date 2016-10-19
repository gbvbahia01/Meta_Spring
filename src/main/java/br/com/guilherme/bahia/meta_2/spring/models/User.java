/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.meta_2.spring.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Guilherme
 */
@Entity
@Table(name = "USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByDescription", query = "SELECT u FROM User u WHERE u.description = :description")})
public class User implements ModelContract {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 45, min = 2, message = "User name must be between 2 and 45")
    @Column(name = "NAME")
    @NotNull(message = "User name required")
    private String name;
    @Size(max = 45)
    @Column(name = "DESCRIPTION")
    private String description;
    
    @JoinTable(name = "USER_PERMISSION",
    joinColumns = {
        @JoinColumn(name = "USER_ID",
        referencedColumnName = "ID")},
    inverseJoinColumns =
    @JoinColumn(name = "PERM_ID",
    referencedColumnName = "ID"))
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permission> userPermissionList;
    
    @JoinColumn(name = "DEPT_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Department dept;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
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

    
    @XmlTransient
    public List<Permission> getUserPermissionList() {
        if(userPermissionList == null){
            userPermissionList = new ArrayList<>();
        }
        return userPermissionList;
    }

    public void setUserPermissionList(List<Permission> userPermissionList) {
        this.userPermissionList = userPermissionList;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department deptId) {
        this.dept = deptId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.guilherme.bahia.meta_2.spring.models.User[ id=" + id + " ]";
    }
    
}
