package forum.hub.community.controller;

import forum.hub.community.cursos.Curso;
import forum.hub.community.cursos.CursoRepository;
import forum.hub.community.respostas.DadosCadastroResposta;
import forum.hub.community.respostas.Resposta;
import forum.hub.community.respostas.RespostaRepository;
import forum.hub.community.topicos.DadosRegistroTopico;
import forum.hub.community.topicos.Topico;
import forum.hub.community.topicos.TopicoRepository;
import forum.hub.community.topicos.atualizacao.AtualizacaoTopico;
import forum.hub.community.topicos.atualizacao.AtualizacaoTopicoRepository;
import forum.hub.community.topicos.atualizacao.DadosAtualizacaoTopico;
import forum.hub.community.topicos.detalhamento.DetalhamentoTopico;
import forum.hub.community.usuarios.Usuario;
import forum.hub.community.usuarios.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

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

    @Autowired
    private RespostaRepository respostaRepository;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<String> registrarTopico(@RequestBody @Valid DadosRegistroTopico dados) {

        //Verifica se já existe o topico no banco de dados
        if (topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            return ResponseEntity
                    .badRequest()
                    .body("Já existe um tópico com esse título e mensagem.");
        }

        // Buscar curso pelo nome
        Curso curso = cursoRepository.findByNomeCurso(dados.nomeCurso())
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
    public ResponseEntity<String> atualizarTopico(@PathVariable Long id,
                                                  @RequestBody @Valid DadosAtualizacaoTopico dadosAtualizacao) {

        Optional<Topico> presenteOuNao = topicoRepository.findById(id);

        //Verifica se o tópico existe no repositório
        if (presenteOuNao.isPresent()) {
            Topico topico = presenteOuNao.get();

            // Atualiza o status
            topico.atualizarStatus(dadosAtualizacao.status());

            // Cria atualização
            AtualizacaoTopico atualizacao = new AtualizacaoTopico(dadosAtualizacao, topico);
            atualizacaoRepository.save(atualizacao);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Não existe nenhum tópico com esse id.");
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<DetalhamentoTopico> listarTopicoPorId(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();

            topico.getRespostas().size();
            topico.getAtualizacoes().size();

            DetalhamentoTopico detalhamento = new DetalhamentoTopico(topico);
            return ResponseEntity.ok(detalhamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Lista todos os métodos em páginas de 10, com suas respectivas respostas e atualizações
    @GetMapping("/listar")
    public ResponseEntity<Page<DetalhamentoTopico>> listarTodosOsTopicos(
            @PageableDefault(size = 10, sort = {"dataCriacao"}, direction = Sort.Direction.ASC) Pageable paginacao) {

        Page<DetalhamentoTopico> page = topicoRepository
                .findAll(paginacao)
                .map(DetalhamentoTopico::new);

        return ResponseEntity.ok(page);
    }

    //Deleta um topico especifico de banco de dados, através do id
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarTopico(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()) {
            topicoRepository.delete(topicoOptional.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/responder/{id}")
    @Transactional
    public ResponseEntity<String> responderTopico(@PathVariable Long id,
                                                  @RequestBody @Valid DadosCadastroResposta dados) {

        //Verifica se o tópico existe
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Tópico não encontrado com o ID fornecido.");
        }

        //Verifica se o autor da resposta existe
        Optional<Usuario> autorOptional = usuarioRepository.findById(dados.idAutor());
        if (autorOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário (autor da resposta) não encontrado.");
        }

        //Cria e salva a resposta
        Resposta resposta = new Resposta(
                null,
                dados.mensagem(),
                LocalDateTime.now(),
                topicoOptional.get(),
                autorOptional.get()
        );

        respostaRepository.save(resposta);

        return ResponseEntity.ok("Resposta registrada com sucesso.");
    }
}