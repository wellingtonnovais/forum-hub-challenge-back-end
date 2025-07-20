package forum.hub.community.topicos.detalhamento;

import forum.hub.community.topicos.Topico;
import forum.hub.community.topicos.atualizacao.DadosAtualizacaoTopico;

import java.util.List;

public record DetalhamentoTopico(
        String id,
        String titulo,
        String mensagem,
        String status,
        String curso,
        String autor,
        String data,
        List<DadosAtualizacaoTopico> atualizacoes
) {
    public DetalhamentoTopico(Topico topico) {
        this(
                topico.getId().toString(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getStatus().toString(),
                topico.getCurso().getNomeCurso(),
                topico.getAutor().getNome(),
                topico.getDataCriacao().toString(),
                topico.getAtualizacoes().stream().map(DadosAtualizacaoTopico::new).toList()
        );
    }
}