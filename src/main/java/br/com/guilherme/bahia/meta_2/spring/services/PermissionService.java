/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.meta_2.spring.services;

import br.com.guilherme.bahia.meta_2.spring.models.Permission;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Guilherme
 */
@Component(value = "perService")
@Transactional
public class PermissionService extends AbstractService<Permission> implements Serializable{

    @PersistenceContext
    private EntityManager em;

    public PermissionService() {
        super(Permission.class);
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
