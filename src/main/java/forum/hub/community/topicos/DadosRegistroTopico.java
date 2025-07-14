package forum.hub.community.topicos;

import forum.hub.community.cursos.CategoriaCurso;

public record DadosRegistroTopico(String mensagem, CategoriaCurso nomeCurso, String titulo) {
}
