/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.volvo.spring.repositories;
import br.com.guilherme.bahia.volvo.spring.models.User;
import java.io.Serializable;
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
public class UserRepositories extends AbstractRepositories<User>  implements Serializable{
    
    	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

    public UserRepositories() {
        super(User.class);
    }

	public EntityManager getEntityManager() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
