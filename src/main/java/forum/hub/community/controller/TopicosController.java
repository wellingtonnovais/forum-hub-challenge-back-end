package forum.hub.community.controller;

import forum.hub.community.cursos.Curso;
import forum.hub.community.cursos.CursoRepository;
import forum.hub.community.topicos.DadosRegistroTopico;
import forum.hub.community.topicos.Topico;
import forum.hub.community.topicos.TopicoRepository;
import forum.hub.community.topicos.atualizacao.AtualizacaoTopico;
import forum.hub.community.topicos.atualizacao.AtualizacaoTopicoRepository;
import forum.hub.community.topicos.atualizacao.DadosAtualizacaoTopico;
import forum.hub.community.usuarios.Usuario;
import forum.hub.community.usuarios.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AtualizacaoTopicoRepository atualizacaoRepository;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<Void> registrarTopico(@RequestBody @Valid DadosRegistroTopico dados) {
        // Buscar curso pelo nome
        Curso curso = cursoRepository.findByCategoria(dados.nomeCurso())
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));

        // Buscar autor fixo ou com base no usuário logado (exemplo simples abaixo)
        Usuario autor = usuarioRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        // Criar o tópico
        Topico topico = new Topico(dados, curso, autor);

        // Salvar no banco
        topicoRepository.save(topico);

        return ResponseEntity.ok().build();
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
