package competitie.manager.services.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.stream.Collectors;

import static competitie.manager.services.security.Secret.SECRET;

@Service
@Transactional
public class JWTTokenService {
    public String getJWTToken(String username) {
        return "Bearer " +
                Jwts.builder()
                        .setId("softtekJWT")
                        .setSubject(username)
                        .claim("authorities",
                                AuthorityUtils
                                        .commaSeparatedStringToAuthorityList("ROLE_USER")
                                        .stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList()))
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 600000))
                        .signWith(SignatureAlgorithm.HS512,
                                SECRET.getBytes())
                        .compact();
    }
}
