package com.github.joseiriel.forumhub;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicos;

    @Autowired
    private UsuarioRepository usuarios;

    @Autowired
    private CursoRepository cursos;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
        var topico = new Topico(dados);
        var usuario = usuarios.findByNome(dados.autor());
        if (usuario != null) {
            topico.setAutor(usuario);
        }
        var curso = cursos.findByNome(dados.curso());
        if (curso != null) {
            topico.setCurso(curso);
        }


        topicos.save(topico);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }
}
