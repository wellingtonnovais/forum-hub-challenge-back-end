package forum.hub.community.respostas;

import forum.hub.community.topicos.Topico;
import forum.hub.community.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;

import javax.xml.crypto.Data;

@Table(name = "resposta")
@Entity(name = "Resposta")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;

    @ManyToOne
    private Topico topico;

    @CreationTimestamp
    private Data dataCriacao;

    @ManyToOne
    private Usuario autor;

    private Boolean solucao;
}
