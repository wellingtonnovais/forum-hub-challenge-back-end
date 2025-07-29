package forum.hub.community.respostas.detalhamento;

import forum.hub.community.respostas.Resposta;

import java.time.format.DateTimeFormatter;

public record DadosDetalhamentoRespostas(
    String mensagem,
    String data,
    String autor
) {
    public DadosDetalhamentoRespostas(Resposta resposta) {
            this(
                    resposta.getMensagem(),
                    resposta.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                    resposta.getAutor().getNome()
            );
        }
    }