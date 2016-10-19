/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.meta_2.spring.services;

import br.com.guilherme.bahia.meta_2.spring.models.ModelContract;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Guilherme
 */
@Transactional
public abstract class AbstractService<T extends ModelContract> implements Serializable {

    public abstract EntityManager getEntityManager();
    private Class<T> entityClass;

    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    public Serializable register(T usr) {
        checkEntity(usr);
        getEntityManager().persist(usr);
        getEntityManager().flush();
        return usr.getId();
    }

    @Transactional
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq
                = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Transactional
    public void delete(T model) {
        getEntityManager().remove(model);
        getEntityManager().flush();
    }

    @Transactional
    public void update(T model) {
        checkEntity(model);
        getEntityManager().merge(model);
        getEntityManager().flush();
    }

    @Transactional
    public T getById(Serializable id) {
        return getEntityManager().find(entityClass, id);
    }

    protected void validadionException(final Set<ConstraintViolation<?>> erros)
            throws IllegalArgumentException {
        String erro = "";
        for (ConstraintViolation cv : erros) {
            erro += cv.getMessage() + " ";
        }
        throw new IllegalArgumentException(erro);
    }

    protected void checkEntity(final ModelContract entity)
            throws IllegalArgumentException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ModelContract>> msgs
                = validator.validate(entity);
        if (!msgs.isEmpty()) {
            validadionToNegocioException(msgs);
        }
    }

    private void validadionToNegocioException(final Set<ConstraintViolation<ModelContract>> erros)
            throws IllegalArgumentException {
        String erro = "";
        for (ConstraintViolation cv : erros) {
            erro += cv.getMessage() + " ";
        }
        throw new IllegalArgumentException(erro);
    }
}
