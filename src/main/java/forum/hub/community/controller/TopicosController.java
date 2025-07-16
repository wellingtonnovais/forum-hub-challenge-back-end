package forum.hub.community.controller;

import forum.hub.community.topicos.DadosRegistroTopico;
import forum.hub.community.topicos.Topico;
import forum.hub.community.topicos.TopicoRepository;
import forum.hub.community.topicos.atualizacao.AtualizacaoTopico;
import forum.hub.community.topicos.atualizacao.AtualizacaoTopicoRepository;
import forum.hub.community.topicos.atualizacao.DadosAtualizacaoTopico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private AtualizacaoTopicoRepository atualizacaoRepository;

    @PostMapping("/registrar")
    @Transactional
    public void resgistrarTopico(@RequestBody @Valid DadosRegistroTopico dadosRegistro) {
        Topico novoTopico = new Topico(dadosRegistro);
        topicoRepository.save(novoTopico);
    }

    @PutMapping("/atualizar/{id}")
    @Transactional
    public void atualizarTopico(@PathVariable Long id,
                                @RequestBody @Valid DadosAtualizacaoTopico dadosAtualizacao) {

        // 1. Busca o tópico existente
        Topico topico = topicoRepository.getReferenceById(id);

        // 2. Atualiza o status no topico original
        topico.atualizarStatus(dadosAtualizacao.status());

        // 3. Cria uma atualização e salva no banco
        AtualizacaoTopico atualizacao = new AtualizacaoTopico(dadosAtualizacao, topico);
        atualizacaoRepository.save(atualizacao);
    }
}
