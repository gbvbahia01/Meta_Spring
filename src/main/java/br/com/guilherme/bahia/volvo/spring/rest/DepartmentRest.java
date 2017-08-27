/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.volvo.spring.rest;

import br.com.guilherme.bahia.volvo.spring.jms.MessageSender;
import br.com.guilherme.bahia.volvo.spring.models.User;
import br.com.guilherme.bahia.volvo.spring.rest.to.DepartmentTo;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Guilherme
 */
@RestController
public class DepartmentRest {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentRest.class);

    @Inject
    private MessageSender messageSender;
    
    @RequestMapping(value = URIConstants.CREATE_DEPARTMENT, method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    public String createDepartment(@RequestBody DepartmentTo depTO) {
        try {
        logger.info("Start createDepartment");
        messageSender.sendMessage(depTO);
        return "Department was put in QUEUE.";
        } catch (Exception e) {
            logger.error("A problem has happened at QUEUE");
            return "A problem has happened trying to put the department at QUEUE";
        }
    }
            
            
}
