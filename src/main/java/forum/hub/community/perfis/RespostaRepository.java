package forum.hub.community.perfis;

import forum.hub.community.respostas.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
}
