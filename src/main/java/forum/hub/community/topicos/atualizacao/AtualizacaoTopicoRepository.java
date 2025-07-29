package forum.hub.community.topicos.atualizacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtualizacaoTopicoRepository extends JpaRepository<AtualizacaoTopico, Long> {
}