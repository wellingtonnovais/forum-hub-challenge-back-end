package forum.hub.community.respostas;

import forum.hub.community.topicos.Topico;
import forum.hub.community.usuarios.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Table(name = "respostas")
@Entity(name = "Resposta")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    @ManyToOne
    private Topico topico;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @ManyToOne
    private Usuario autor;

    public Resposta(Object o, @NotBlank String mensagem, LocalDateTime now, Topico topico, Usuario usuario) {
        this.mensagem = mensagem;
        this.topico = topico;
        this.dataCriacao = now;
        this.autor = usuario;
    }
}
