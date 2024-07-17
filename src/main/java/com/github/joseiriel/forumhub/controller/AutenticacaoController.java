package com.github.joseiriel.forumhub.controller;

import com.github.joseiriel.forumhub.domain.Usuario;
import com.github.joseiriel.forumhub.dados.DadosAutenticacao;
import com.github.joseiriel.forumhub.infra.security.DadosTokenJWT;
import com.github.joseiriel.forumhub.infra.security.JWTTokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JWTTokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
            Authentication authentication = manager.authenticate(authenticationToken);
            var token = tokenService.gerarToken((Usuario) authentication.getPrincipal());
            return ResponseEntity.ok(new DadosTokenJWT(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).build();
        }
    }
}


