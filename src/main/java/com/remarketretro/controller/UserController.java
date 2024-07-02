package com.remarketretro.controller;

import com.remarketretro.entity.User;
import com.remarketretro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    /**
     * Cadastra novo usuário
     * @param user = informações do usuário
     * @return retorna as informações cadastradas
     */
    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    /**
     * Indica que uma página específica só é acessível aos administradores
     * @return retorna uma mensagem de erro
     */
    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "Essa página só é acessível ao usuário administrador";
    }

    /**
     * Indica que uma página específica só é acessível aos usuários compradores
     * @return retorna uma mensagem de erro
     */
    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "Essa página só é acessível ao usuário";
    }
}