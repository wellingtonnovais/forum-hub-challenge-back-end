package forum.hub.community.cursos;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "curso")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCurso;

    @Enumerated(EnumType.STRING)
    private CategoriaCurso categoria;

    public Curso(CategoriaCurso categoriaCurso) {
        this.categoria = categoriaCurso;
        this.nomeCurso = categoriaCurso.getNomeExibicao();
    }

}
