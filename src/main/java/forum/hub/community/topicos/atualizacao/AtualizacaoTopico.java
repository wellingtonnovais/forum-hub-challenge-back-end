package forum.hub.community.topicos.atualizacao;

import forum.hub.community.topicos.Topico;
import forum.hub.community.usuarios.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

import java.time.LocalDateTime;

@Entity
@Table(name = "topico_atualisacao")
public class AtualizacaoTopico extends Topico {

    private Long id;

    private String titulo;
    private String mensagem;
    private LocalDateTime data;

    @ManyToOne
    private Usuario editor;

    @ManyToOne
    private Topico topico;

    public AtualizacaoTopico(@Valid DadosAtualizacaoTopico dadosAtualizacao) {
        this.titulo = dadosAtualizacao.titulo();
        this.mensagem = dadosAtualizacao.mensagem();
        this.topico = new Topico(dadosAtualizacao.status());
    }
}
