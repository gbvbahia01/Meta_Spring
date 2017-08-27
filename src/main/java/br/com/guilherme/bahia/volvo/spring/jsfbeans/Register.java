/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.volvo.spring.jsfbeans;

import br.com.guilherme.bahia.volvo.spring.models.ModelContract;
import br.com.guilherme.bahia.volvo.spring.repositories.AbstractRepositories;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Guilherme
 */
public abstract class Register<T extends ModelContract> implements Serializable {

    public abstract AbstractRepositories<T> getService();

    public void register(T entityClass) {
        if(entityClass.getId() == null){
            getService().register(entityClass);
        }else{
            getService().update(entityClass);
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("The " + entityClass.getClass().getSimpleName() + ": "
                        + entityClass.getName() + " Is Registered Successfully"));
        return;
    }

    public void remove(T entityClass) {
        getService().delete(entityClass);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("The " + entityClass.getClass().getSimpleName() + ": "
                        + entityClass.getName() + " Is Removed Successfully"));
        return;
    }

    protected void errorMsg(IllegalArgumentException e) {
        FacesMessage message = new FacesMessage();
        message.rendered();
        message.setSummary(e.getMessage());
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
