/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.volvo.spring.rest;

import br.com.guilherme.bahia.volvo.spring.models.User;
import br.com.guilherme.bahia.volvo.spring.rest.to.UserTo;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.guilherme.bahia.volvo.spring.repositories.DepartmentRepositorie;
import br.com.guilherme.bahia.volvo.spring.repositories.PermissionRepositorie;
import br.com.guilherme.bahia.volvo.spring.repositories.UserRepositorie;

/**
 *
 * @author Guilherme
 */
@RestController
public class UserRest {

    private static final Logger logger = LoggerFactory.getLogger(UserRest.class);

    @Inject
    private UserRepositorie userRep;
    @Inject
    private PermissionRepositorie perRep;
    @Inject
    private DepartmentRepositorie dptRep;

    @RequestMapping(value = URIConstants.GET_USER, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    UserTo getUser(@PathVariable("id") int usrId) {
        logger.info("Start getUser. ID=" + usrId);
        return new UserTo(userRep.findOne(usrId));
    }

    @RequestMapping(value = URIConstants.GET_ALL_USER, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<UserTo> getUsers() {
        logger.info("Start findAll");
        return UserTo.toList(userRep.findAll());
    }

    @RequestMapping(value = URIConstants.CREATE_USER, method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    public @ResponseBody
    UserTo createUser(@RequestBody UserTo userTo) {
        logger.info("Start createUser");
        User user = toUser(userTo);
        user.setId(null);
        return new UserTo(userRep.save(toUser(userTo)));
    }

    @RequestMapping(value = URIConstants.UPDATE_USER, method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    public @ResponseBody
    UserTo updateUser(@RequestBody UserTo userTo) {
        logger.info("Start updateUser");
        if (userTo.getId() == null) {
            throw new IllegalArgumentException("User Id cannot be NULL");
        }
        User user = toUser(userTo);
        userRep.save(user);
        UserTo to = new UserTo(user);
        return to;
    }

    @RequestMapping(value = URIConstants.DELETE_USER, method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    public ResponseEntity removeUser(@PathVariable("id") int usrId) {
        logger.info("Start updateUser");
        if (usrId <= 0) {
            throw new IllegalArgumentException("Invalid USER ID");
        }
        userRep.delete(usrId);
        return new ResponseEntity<UserTo>(HttpStatus.OK);
    }

    private User toUser(UserTo to) {
        User usr = new User();
        usr.setName(to.getName());
        usr.setDescription(to.getDescription());
        usr.setId(to.getId());
        if (to.getIdDepartment() != null) {
            usr.setDept(dptRep.findOne(to.getIdDepartment()));
        }
        if (!to.getIdPermissions().isEmpty()) {
            for (Integer pId : to.getIdPermissions()) {
                usr.getUserPermissionList().add(perRep.findOne(pId));
            }
        }
        return usr;
    }
}
