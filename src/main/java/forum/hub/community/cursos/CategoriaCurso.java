package forum.hub.community.cursos;

public enum CategoriaCurso {
    JAVA("Java"),
    PYTHON("Python"),
    PHP("PHP"),
    KOTLIN("Kotlin"),
    HTML("HTML"),
    CSS("CSS"),
    JAVASCRIPT("JavaScript"),
    CHATGPT("ChatGPT");

    private final String nomeExibicao;

    CategoriaCurso(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }
}