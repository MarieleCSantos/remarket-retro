package com.remarketretro.controller;

import com.remarketretro.entity.Role;
import com.remarketretro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * Endpoint usado para criar novas permissões na base
     * @param role = objeto que contém o nome da permissão e sua descrição
     * @return retorna as informações inseridas na base
     */
    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }
}
