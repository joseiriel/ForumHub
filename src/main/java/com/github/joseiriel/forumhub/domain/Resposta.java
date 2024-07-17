package com.github.joseiriel.forumhub.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Resposta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;
    private LocalDateTime data;
    private Boolean solucao;

    @ManyToOne
    private Topico topico;

    @ManyToOne
    private Usuario autor;
}