package forum.hub.community.cursos;

public enum NomeCursos {
    JAVA_COMPLETO("Java Completo", CategoriaCurso.JAVA),
    PYTHON_DO_ZERO("Python do Zero", CategoriaCurso.PYTHON),
    HTML_PARA_INICIANTES("HTML Para iniciantes", CategoriaCurso.HTML),
    CSS_PARA_INICIANTES("CSS Para iniciantes", CategoriaCurso.CSS),
    CURSO_DE_JAVASCRIPT("Curso de JavaScript", CategoriaCurso.JAVASCRIPT),
    KOTLIN_ESSENCIAL("Kotlin Essencial", CategoriaCurso.KOTLIN);

    private final String nomeExibicao;
    private final CategoriaCurso categoria;

    NomeCursos(String nomeExibicao, CategoriaCurso categoria) {
        this.nomeExibicao = nomeExibicao;
        this.categoria = categoria;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public CategoriaCurso getCategoria() {
        return categoria;
    }
}