/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.meta_2.spring.models;

import java.io.Serializable;

/**
 *
 * @author Guilherme
 */
public interface ModelContract extends Serializable{
    
    Serializable getId();
    String getName();
}
