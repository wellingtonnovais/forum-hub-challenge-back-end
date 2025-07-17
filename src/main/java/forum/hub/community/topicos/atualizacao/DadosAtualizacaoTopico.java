package forum.hub.community.topicos.atualizacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(

        @NotBlank
        String titulo,

        @NotBlank
        String mensagem,

        @NotNull
        StatusTopico status) {
        public DadosAtualizacaoTopico(AtualizacaoTopico atualizacao) {
                this(
                        atualizacao.getTitulo(),
                        atualizacao.getMensagem(),
                        atualizacao.getTopico().getStatus()
                );
        }
}
