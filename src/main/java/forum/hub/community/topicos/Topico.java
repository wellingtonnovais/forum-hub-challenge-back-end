package forum.hub.community.topicos;

import forum.hub.community.cursos.Curso;
import forum.hub.community.respostas.Resposta;
import forum.hub.community.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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

    private String status;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Resposta> respostas;

    public Topico(DadosRegistroTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.curso = new Curso(dados.nomeCurso());
    }
}