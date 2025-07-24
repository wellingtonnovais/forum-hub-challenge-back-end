package forum.hub.community.topicos.detalhamento;

import forum.hub.community.respostas.detalhamento.DadosDetalhamentoRespostas;
import forum.hub.community.topicos.Topico;
import forum.hub.community.topicos.atualizacao.DadosAtualizacaoTopico;

import java.time.format.DateTimeFormatter;
import java.util.List;

public record DetalhamentoTopico(
        String id,
        String titulo,
        String mensagem,
        String status,
        String curso,
        String categoria,
        String autor,
        String data,
        List<DadosAtualizacaoTopico> atualizacoes,
        List<DadosDetalhamentoRespostas> respostas
) {
    public DetalhamentoTopico(Topico topico) {
        this(
                topico.getId().toString(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getStatus().toString(),
                topico.getCurso().getNomeCurso().getNomeExibicao(),
                topico.getCurso().getCategoria().getNomeExibicao(),
                topico.getAutor().getNome(),
                topico.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                topico.getAtualizacoes().stream().map(DadosAtualizacaoTopico::new).toList(),
                topico.getRespostas().stream().map(DadosDetalhamentoRespostas::new).toList()
        );
    }
}