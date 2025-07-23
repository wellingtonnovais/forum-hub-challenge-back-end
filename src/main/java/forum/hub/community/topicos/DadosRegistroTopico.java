package forum.hub.community.topicos;

import forum.hub.community.cursos.CategoriaCurso;
import forum.hub.community.cursos.NomeCursos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosRegistroTopico(

        @NotBlank
        String titulo,

        @NotBlank
        String mensagem,

        @NotNull
        NomeCursos nomeCurso) {


}
