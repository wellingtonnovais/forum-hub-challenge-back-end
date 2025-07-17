package forum.hub.community.cursos;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository <Curso, Long>{

//    Optional<Curso> findByNomeCurso(@NotNull String nomeCurso);
Optional<Curso> findByCategoria(@NotNull CategoriaCurso categoria);
}