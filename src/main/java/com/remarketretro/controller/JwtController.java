package com.remarketretro.controller;

import com.remarketretro.entity.JwtRequest;
import com.remarketretro.entity.JwtResponse;
import com.remarketretro.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    /**
     * Cria um token com base nas informações do usuário
     * @param jwtRequest = objeto que contem os dados de login do usuário atual
     * @return retorna usuário e o token gerado
     * @throws Exception propaga a exceção
     */
    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}
