package forum.hub.community.topicos;

import forum.hub.community.cursos.Curso;
import forum.hub.community.respostas.Resposta;
import forum.hub.community.topicos.atualizacao.AtualizacaoTopico;
import forum.hub.community.usuarios.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resposta> respostas;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AtualizacaoTopico> atualizacoes = new ArrayList<>();

    public Topico(DadosRegistroTopico dados, Curso curso, Usuario autor) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.status = StatusTopico.ABERTO;
        this.curso = curso;
        this.autor = autor;
    }

    public Topico(@NotNull StatusTopico status) {
        this.status = status;
    }

    public void atualizarStatus(StatusTopico status) {
        this.status = status;
    }
}