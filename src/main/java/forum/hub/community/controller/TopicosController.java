package forum.hub.community.controller;

import forum.hub.community.topicos.DadosRegistroTopico;
import forum.hub.community.topicos.Topico;
import forum.hub.community.topicos.TopicoRepository;
import forum.hub.community.topicos.atualizacao.AtualizacaoTopico;
import forum.hub.community.topicos.atualizacao.DadosAtualizacaoTopico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping("/registrar")
    @Transactional
    public void resgistrarTopico(@RequestBody @Valid DadosRegistroTopico dadosRegistro) {
        repository.save(new Topico(dadosRegistro));
    }

    @PutMapping("/atualizar")
    @Transactional
    public void atualizarTopico(@RequestBody @Valid DadosAtualizacaoTopico dadosAtualizacao) {
        repository.save(new AtualizacaoTopico(dadosAtualizacao));
    }
}
