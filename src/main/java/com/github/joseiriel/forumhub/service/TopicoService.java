package com.github.joseiriel.forumhub.service;

import com.github.joseiriel.forumhub.domain.Curso;
import com.github.joseiriel.forumhub.domain.Topico;
import com.github.joseiriel.forumhub.domain.Usuario;
import com.github.joseiriel.forumhub.dados.DadosCadastroTopico;
import com.github.joseiriel.forumhub.dados.DadosTopicoResposta;
import com.github.joseiriel.forumhub.infra.exception.ValidacaoException;
import com.github.joseiriel.forumhub.infra.security.JWTTokenService;
import com.github.joseiriel.forumhub.repository.CursoRepository;
import com.github.joseiriel.forumhub.repository.TopicoRepository;
import com.github.joseiriel.forumhub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {
    @Autowired
    private JWTTokenService tokenService;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public DadosTopicoResposta cadastrar(DadosCadastroTopico dados, String token) {
        validarCadastroTopico(dados);
        Usuario autor = findAuthorByToken(token);
        Curso curso = cursoRepository.getReferenceById(dados.curso_id());
        Topico topico = new Topico(dados.titulo(), dados.mensagem(), curso, autor);
        return new DadosTopicoResposta(topicoRepository.save(topico));
    }

    public DadosTopicoResposta buscar(Long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        return new DadosTopicoResposta(topico);
    }

    public List<DadosTopicoResposta> listar() {
        List<Topico> topicos = topicoRepository.findAllByOrderByDataDesc();
        return topicos.stream()
                .map(DadosTopicoResposta::new)
                .toList();
    }

    @Transactional
    public DadosTopicoResposta atualizar(Long id, DadosCadastroTopico dados, String token) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        Usuario autor = findAuthorByToken(token);
        if (!autor.equals(topico.getAutor())) {
            throw new RuntimeException("Usuário sem permissão");
        }
        validarCadastroTopico(dados);
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        Curso curso = cursoRepository.getReferenceById(dados.curso_id());
        topico.setCurso(curso);
        return new DadosTopicoResposta(topico);
    }

    @Transactional
    public void remover(Long id, String token) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        Usuario autor = findAuthorByToken(token);

        if (!autor.equals(topico.getAutor())) {
            throw new RuntimeException("Usuário sem permissão");
        }

        topicoRepository.deleteById(id);
    }

    private void validarCadastroTopico(DadosCadastroTopico dados) {
        if (!cursoRepository.existsById(dados.curso_id())) {
            throw new ValidacaoException("Curso não encontrado");
        }
    }

    public Usuario findAuthorByToken(String token) {
        String email = tokenService.getSubject(token.replace("Bearer ", ""));
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + email));
    }
}
