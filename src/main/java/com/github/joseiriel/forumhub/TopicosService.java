package com.github.joseiriel.forumhub;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicos;

    @Autowired
    private UsuarioRepository usuarios;

    @Autowired
    private

    @Transactional
    public void cadastrar(DadosCadastroTopico dadosCadastroTopico) {
        Usuario usuarioExistente =
    }
}
