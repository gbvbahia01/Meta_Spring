/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.volvo.spring.jms;

import br.com.guilherme.bahia.volvo.spring.jms.setup.MessagingConfiguration;
import br.com.guilherme.bahia.volvo.spring.models.Department;
import br.com.guilherme.bahia.volvo.spring.repositories.DepartmentRepositorie;
import br.com.guilherme.bahia.volvo.spring.rest.to.DepartmentTo;
import java.io.StringReader;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

/**
 *
 * @author Guilherme
 */
@Component
public class MessageReceiver {
    private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
    
    @Inject
    private DepartmentRepositorie departmentRepositorie;
    
    @JmsListener(destination = MessagingConfiguration.DEP_QUEUE)
    public void receiveMessage(final Message<String> message) throws JMSException {
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        MessageHeaders headers =  message.getHeaders();
        logger.info("Application : headers received : {}", headers);
         
        String response = message.getPayload();
        logger.info("Application : response received : {}", response);
        
        DepartmentTo departmentTo = xmlToDep(response);
        logger.info("Application : departmentTo received : {}", departmentTo);
        
        departmentRepositorie.save(convertToDepartment(departmentTo));
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    
    private DepartmentTo xmlToDep(String xml) {
        try {
        JAXBContext context = JAXBContext.newInstance(DepartmentTo.class);
        Unmarshaller un = context.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        return (DepartmentTo) un.unmarshal(reader);
        } catch (Exception e) {
            logger.error("Error using JAXBContext API", e);
            throw new RuntimeException(e);
        }
    }
    
    private Department convertToDepartment(DepartmentTo to) {
        Department dep = new Department();
        dep.setId(to.getId());
        dep.setName(to.getName());
        dep.setDescription(to.getDescription());
        return dep;
    }
}
