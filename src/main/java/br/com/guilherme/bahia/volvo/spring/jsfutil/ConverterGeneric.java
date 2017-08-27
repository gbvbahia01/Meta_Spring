/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.volvo.spring.jsfutil;

import br.com.guilherme.bahia.volvo.spring.models.ModelContract;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Guilherme
 */
@FacesConverter(value = "genericConverter")
public class ConverterGeneric implements Converter {
     @Override
    public Object getAsObject(FacesContext arg0, UIComponent componente,
            String valor) {
        if (valor != null) {
            return this.getAttributesFrom(componente).get(valor);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent componente,
            Object valor) {
        if (valor != null && !"".equals(valor)) {
            ModelContract ei = (ModelContract) valor;
            this.addAttribute(componente, ei);
            return ei.getId().toString();
        }
        return null;
    }

    protected void addAttribute(UIComponent component, ModelContract obj) {
        this.getAttributesFrom(component).put(obj.getId().toString(), obj);
    }

    protected Map getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }
}
