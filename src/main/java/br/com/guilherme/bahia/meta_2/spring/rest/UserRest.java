/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.guilherme.bahia.meta_2.spring.rest;

import br.com.guilherme.bahia.meta_2.spring.models.User;
import br.com.guilherme.bahia.meta_2.spring.rest.to.UserTo;
import br.com.guilherme.bahia.meta_2.spring.services.DepartmentService;
import br.com.guilherme.bahia.meta_2.spring.services.PermissionService;
import br.com.guilherme.bahia.meta_2.spring.services.UserService;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Guilherme
 */
@Controller
public class UserRest {

    private static final Logger logger = LoggerFactory.getLogger(UserRest.class);

    @Inject
    private UserService userService;
    @Inject
    private PermissionService perService;
    @Inject
    private DepartmentService dptService;

    @RequestMapping(value = URIConstants.GET_USER, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    UserTo getUser(@PathVariable("id") int usrId) {
        logger.info("Start getUser. ID=" + usrId);
        return new UserTo(userService.getById(usrId));
    }

    @RequestMapping(value = URIConstants.GET_ALL_USER, method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<UserTo> getUsers() {
        logger.info("Start findAll");
        return UserTo.toList(userService.findAll());
    }

    @RequestMapping(value = URIConstants.CREATE_USER, method = RequestMethod.POST,
            produces = "application/json", consumes = "application/json")
    public @ResponseBody
    UserTo createUser(@RequestBody UserTo userTo) {
        logger.info("Start createUser");
        User user = toUser(userTo);
        user.setId(null);
        Integer id = (Integer) userService.register(user);
        UserTo to = new UserTo(user);
        to.setId(id);
        return to;
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
        userService.update(user);
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
        userService.delete(userService.getById(usrId));
        return new ResponseEntity<UserTo>(HttpStatus.OK);
    }

    private User toUser(UserTo to) {
        User usr = new User();
        usr.setName(to.getName());
        usr.setDescription(to.getDescription());
        usr.setId(to.getId());
        if (to.getIdDepartment() != null) {
            usr.setDept(dptService.getById(to.getIdDepartment()));
        }
        if (!to.getIdPermissions().isEmpty()) {
            for (Integer pId : to.getIdPermissions()) {
                usr.getUserPermissionList().add(perService.getById(pId));
            }
        }
        return usr;
    }
}
