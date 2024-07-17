package com.github.joseiriel.forumhub.dados;

import com.github.joseiriel.forumhub.domain.Topico;

import java.time.LocalDateTime;

public record DadosTopicoResposta(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String status,
        String nomeCurso,
        String nomeAutor
) {
    public DadosTopicoResposta(Topico topico) {
        this(
            topico.getId(),
            topico.getTitulo(),
            topico.getMensagem(),
            topico.getData(),
            topico.getStatus(),
            topico.getCurso().getNome(),
            topico.getAutor().getNome()
        );
    }
}