package forum.hub.community.cursos;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cursos")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nome_curso")
    private NomeCursos nomeCurso;

    @Enumerated(EnumType.STRING)
    private CategoriaCurso categoria;

    public Curso(NomeCursos nomeCurso) {
        this.nomeCurso = nomeCurso;
        this.categoria = nomeCurso.getCategoria(); // atribui a categoria do curso automaticamente
    }
}