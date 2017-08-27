/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.volvo.spring.jms;

import br.com.guilherme.bahia.volvo.spring.rest.to.DepartmentTo;
import java.io.StringWriter;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 *
 * @author Guilherme
 */
@Component
public class MessageSender {

    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    @Inject
    private JmsTemplate jmsTemplate;

    public void sendMessage(final DepartmentTo dep) throws Exception {
        try {
            jmsTemplate.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    String msg = depToXml(dep);
                    TextMessage textMsg = session.createTextMessage(msg);
                    return textMsg;
                }
            });
        } catch (Exception e) {
            logger.error("A problem has happened sendMessage at QUEUE", e);
            throw e;
        }
    }

    private String depToXml(DepartmentTo dep) {
        try {
            JAXBContext context = JAXBContext.newInstance(DepartmentTo.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            m.marshal(dep, sw);
            return sw.toString();
        } catch (Exception e) {
            logger.error("Error using JAXBContext API", e);
            throw new RuntimeException(e);
        }
    }
}
