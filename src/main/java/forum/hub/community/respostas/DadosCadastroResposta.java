package forum.hub.community.respostas;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroResposta(
        @NotBlank String mensagem
) {
}
