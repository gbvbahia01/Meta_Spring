/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.volvo.spring.rest;

/**
 *
 * @author Guilherme
 */
public class URIConstants {
    	public static final String GET_USER = "/rest/usr/{id}";
	public static final String GET_ALL_USER = "/rest/usrs";
	public static final String CREATE_USER = "/rest/usr/create";
        public static final String UPDATE_USER = "/rest/usr/update";
	public static final String DELETE_USER = "/rest/usr/delete/{id}";
        
        public static final String CREATE_DEPARTMENT = "/rest/dep/create";
}
