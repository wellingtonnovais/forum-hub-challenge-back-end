package forum.hub.community.topicos;

import forum.hub.community.cursos.CategoriaCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosRegistroTopico(

        @NotBlank
        String mensagem,

        @NotNull
        CategoriaCurso nomeCurso,

        @NotBlank
        String titulo) {
}
