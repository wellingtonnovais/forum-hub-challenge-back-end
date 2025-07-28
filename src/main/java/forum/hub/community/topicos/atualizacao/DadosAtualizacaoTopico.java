package forum.hub.community.topicos.atualizacao;

import forum.hub.community.topicos.StatusTopico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.format.DateTimeFormatter;

public record DadosAtualizacaoTopico(

        @NotBlank
        String titulo,

        @NotBlank
        String mensagem,

        @NotNull
        StatusTopico status,

        String data
) {
        public DadosAtualizacaoTopico(AtualizacaoTopico atualizacao) {
                this(
                        atualizacao.getTitulo(),
                        atualizacao.getMensagem(),
                        atualizacao.getTopico().getStatus(),
                        atualizacao.getDataAtualizacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                );
        }
}