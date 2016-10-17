/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.meta_2.spring.services;
import br.com.guilherme.bahia.meta_2.spring.models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Guilherme
 */
@Component
@Transactional
public class UserService extends AbstractService<User> {
    
    	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

    public UserService() {
        super(User.class);
    }

	public EntityManager getEntityManager() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
