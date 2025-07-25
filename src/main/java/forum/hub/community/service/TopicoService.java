package forum.hub.community.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TopicoService {

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

    public ResponseEntity<String> registrarTopico(DadosRegistroTopico dados) {
        if (topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            return ResponseEntity.badRequest().body("Já existe um tópico com esse título e mensagem.");
        }

        Curso curso = cursoRepository.findByNomeCurso(dados.nomeCurso())
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario autor = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Topico topico = new Topico(dados, curso, autor);
        topicoRepository.save(topico);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<DetalhamentoTopico> listarPorId(Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            topico.getRespostas().size();
            topico.getAtualizacoes().size();

            DetalhamentoTopico detalhamento = new DetalhamentoTopico(topico);
            return ResponseEntity.ok(detalhamento);
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Page<DetalhamentoTopico>> listarTodos(Pageable paginacao) {
        Page<DetalhamentoTopico> page = topicoRepository
                .findAll(paginacao)
                .map(DetalhamentoTopico::new);

        return ResponseEntity.ok(page);
    }

    public ResponseEntity<String> responderTopico(Long id, DadosCadastroResposta dados) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Tópico não encontrado.");
        }

        Topico topico = topicoOptional.get();

        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuarioLogado = usuarioRepository.findByEmail(emailUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuário logado não encontrado"));

        if (topico.getAutor().equals(usuarioLogado)) {
            return ResponseEntity.badRequest().body("Você não pode responder ao seu próprio tópico.");
        }

        Resposta resposta = new Resposta(
                null,
                dados.mensagem(),
                LocalDateTime.now(),
                topico,
                usuarioLogado
        );

        respostaRepository.save(resposta);

        return ResponseEntity.ok("Resposta registrada com sucesso.");
    }

    public ResponseEntity<String> atualizarTopico(Long id, DadosAtualizacaoTopico dadosAtualizacao) {
        Optional<Topico> presenteOuNao = topicoRepository.findById(id);
        if (presenteOuNao.isEmpty()) {
            return ResponseEntity.badRequest().body("Não existe nenhum tópico com esse id.");
        }

        Topico topico = presenteOuNao.get();
        Usuario usuarioLogado = getUsuarioLogado();

        if (!topico.getAutor().equals(usuarioLogado) && !usuarioLogado.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não tem permissão para atualizar este tópico.");
        }

        topico.atualizarStatus(dadosAtualizacao.status());

        AtualizacaoTopico atualizacao = new AtualizacaoTopico(dadosAtualizacao, topico);
        atualizacaoRepository.save(atualizacao);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> deletarTopico(Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = topicoOptional.get();
        Usuario usuarioLogado = getUsuarioLogado();

        if (!topico.getAutor().equals(usuarioLogado) && !usuarioLogado.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Você não tem permissão para deletar este tópico.");
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Usuario getUsuarioLogado() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuário logado não encontrado"));
    }


}

