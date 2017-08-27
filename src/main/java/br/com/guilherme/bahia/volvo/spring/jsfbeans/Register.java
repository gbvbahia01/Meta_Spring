/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.volvo.spring.jsfbeans;

import br.com.guilherme.bahia.volvo.spring.models.ModelContract;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Guilherme
 */
public abstract class Register<T extends ModelContract, R extends JpaRepository> implements Serializable {

    public abstract R getRepository();

    public void register(T entityClass) {
            getRepository().save(entityClass);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("The " + entityClass.getClass().getSimpleName() + ": "
                        + entityClass.getName() + " Was Registered Successfully"));
        return;
    }

    public void remove(T entityClass) {
        getRepository().delete(entityClass.getId());
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("The " + entityClass.getClass().getSimpleName() + ": "
                        + entityClass.getName() + " Was Removed Successfully"));
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
