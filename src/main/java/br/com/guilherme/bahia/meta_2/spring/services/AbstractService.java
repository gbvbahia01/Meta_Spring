/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.meta_2.spring.services;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Guilherme
 */
public abstract class AbstractService<T> {

    public abstract EntityManager getEntityManager();
    private Class<T> entityClass;

    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    
    @Transactional
    public void register(T usr) {
        getEntityManager().persist(usr);
    }

    @Transactional
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq
                = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    @Transactional
    public void delete(T model){
        getEntityManager().remove(model);
        getEntityManager().flush();
    }
    
    @Transactional
    public void update(T model){
        getEntityManager().merge(model);
    }
    
    @Transactional
    public T getById(Serializable id){
        return getEntityManager().find(entityClass, id);
    }
}
