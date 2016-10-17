/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.meta_2.spring.jsfbeans;

import br.com.guilherme.bahia.meta_2.spring.models.ModelContract;
import br.com.guilherme.bahia.meta_2.spring.services.AbstractService;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Guilherme
 */
public abstract class Register<T extends ModelContract> {

    public abstract AbstractService<T> getService();

    public void register(T entityClass) {
        getService().register(entityClass);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("The " + entityClass.getClass().getSimpleName() + ": "
                        + entityClass.getName() + " Is Registered Successfully"));
        return;
    }
}
