/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.meta_2.spring.services;

import br.com.guilherme.bahia.meta_2.spring.models.User;
import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Guilherme
 */
public abstract class AbstractService<T> {

    public abstract EntityManager getEm();

    @Transactional
    public void register(T usr) {
        getEm().persist(usr);
    }

}
