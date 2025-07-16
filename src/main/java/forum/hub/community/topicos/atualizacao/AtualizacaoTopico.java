package forum.hub.community.topicos.atualizacao;

import forum.hub.community.topicos.Topico;
import forum.hub.community.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topico_atualizacao")
@Getter
@NoArgsConstructor
public class AtualizacaoTopico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;

    private LocalDateTime data;

    @ManyToOne
    private Usuario editor;

    @ManyToOne
    private Topico topico;

    public AtualizacaoTopico(DadosAtualizacaoTopico dados, Topico topico) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.data = LocalDateTime.now();
        this.topico = topico;
    }

}

