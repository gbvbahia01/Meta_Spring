/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.meta_2.spring.rest;

import br.com.guilherme.bahia.meta_2.spring.models.User;
import br.com.guilherme.bahia.meta_2.spring.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
/**
 *
 * @author Guilherme
 */
@Controller
public class UserRest {

    private static final Logger logger = LoggerFactory.getLogger(UserRest.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = URIConstants.GET_USER, method = RequestMethod.GET)
    public @ResponseBody
    User getUser(@PathVariable("id") int usrId) {
        logger.info("Start getUser. ID=" + usrId);

        return userService.getById(usrId);
    }
}
