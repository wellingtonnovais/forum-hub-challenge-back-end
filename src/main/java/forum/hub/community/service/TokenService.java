package forum.hub.community.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import forum.hub.community.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String segredo;

    @Value("${jwt.expiration}")
    private String expiracao;

    public String gerarToken(Usuario usuario) {
        var algoritmo = Algorithm.HMAC256(segredo);
        return JWT.create()
                .withSubject(usuario.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(expiracao)))
                .sign(algoritmo);
    }

    public String getSubject(String tokenJWT) {
        var algoritmo = Algorithm.HMAC256(segredo);
        return JWT.require(algoritmo)
                .build()
                .verify(tokenJWT)
                .getSubject();
    }
}