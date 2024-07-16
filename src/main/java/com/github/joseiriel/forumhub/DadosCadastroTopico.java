package com.github.joseiriel.forumhub;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(
        @NotBlank
        String titulo,
        @NotNull
        Long idAutor,
        @NotBlank
        String mensagem,
        @NotBlank
        String status,
        @NotNull
        Long idCurso
) {
}
