package br.com.alura.easybill.easybill.config.security;

import br.com.alura.easybill.easybill.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    @Value("${easybill.jwt.expiration}")
    private String expiration;

    @Value("${easybill.jwt.secret}")
    private String secret;



    public String geraToken(Authentication authentication){
        Usuario logado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("API easybill")
                .setSubject(logado.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValido(String token){
        try{
        Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
        return true;

        }catch(Exception e){
            return false;
        }
    }

    public Long getIdUsiario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
