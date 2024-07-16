package com.github.joseiriel.forumhub;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime data;
    private String status;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany(mappedBy = "topico")
    private List<Resposta> respostas;

    public Topico(DadosCadastroTopico dados) {
        titulo = dados.titulo();
        mensagem = dados.mensagem();
        data = LocalDateTime.now();
        status = dados.status();
        autor = dados.autor();
        curso = dados.curso();
    }
}
