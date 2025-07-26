package forum.hub.community.service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String senha

) {}