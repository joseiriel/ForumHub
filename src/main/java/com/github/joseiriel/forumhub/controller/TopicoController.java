package com.github.joseiriel.forumhub.controller;

import com.github.joseiriel.forumhub.dados.DadosCadastroTopico;
import com.github.joseiriel.forumhub.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid DadosCadastroTopico dadosCadastro,
            UriComponentsBuilder uriBuilder
    ) {
        var dadosDetalhamento = service.cadastrar(dadosCadastro, token);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(dadosDetalhamento.id()).toUri();
        return ResponseEntity.created(uri).body(dadosDetalhamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable Long id){
        return ResponseEntity.ok(service.buscar(id));
    }

    @GetMapping
    public ResponseEntity listar(){
        return ResponseEntity.ok(service.listar());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody @Valid DadosCadastroTopico dados
    ){
        return ResponseEntity.ok(service.atualizar(id, dados, token));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id
    ) {
        service.remover(id, token);
        return ResponseEntity.noContent().build();
    }
}