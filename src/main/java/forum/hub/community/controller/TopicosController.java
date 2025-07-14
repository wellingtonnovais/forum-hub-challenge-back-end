package forum.hub.community.controller;

import forum.hub.community.topicos.DadosRegistroTopico;
import forum.hub.community.topicos.Topico;
import forum.hub.community.topicos.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public void resgistrarTopico(@RequestBody DadosRegistroTopico dados) {
        repository.save(new Topico(dados));
    }
}
