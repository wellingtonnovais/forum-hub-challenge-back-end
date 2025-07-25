package forum.hub.community.controller;

import forum.hub.community.respostas.DadosCadastroResposta;
import forum.hub.community.service.TopicoService;
import forum.hub.community.topicos.DadosRegistroTopico;
import forum.hub.community.topicos.atualizacao.DadosAtualizacaoTopico;
import forum.hub.community.topicos.detalhamento.DetalhamentoTopico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarTopico(@RequestBody @Valid DadosRegistroTopico dados) {
        return topicoService.registrarTopico(dados);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DetalhamentoTopico> listarTopicoPorId(@PathVariable Long id) {
        return topicoService.listarPorId(id);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DetalhamentoTopico>> listarTodos(
            @PageableDefault(size = 10, sort = {"dataCriacao"}, direction = Sort.Direction.ASC) Pageable paginacao) {
        return topicoService.listarTodos(paginacao);
    }

    @PostMapping("/responder/{id}")
    public ResponseEntity<String> responderTopico(@PathVariable Long id,
                                                  @RequestBody @Valid DadosCadastroResposta dados) {
        return topicoService.responderTopico(id, dados);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarTopico(@PathVariable Long id,
                                                  @RequestBody @Valid DadosAtualizacaoTopico dadosAtualizacao) {
        return topicoService.atualizarTopico(id, dadosAtualizacao);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarTopico(@PathVariable Long id) {
        return topicoService.deletarTopico(id);
    }
}