package de.Garkolym.boilerplate.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenHelper {

    @Value("${app.name}")
    private String APP_NAME;
    @Value("${jwt.secret}")
    private String SECRET;
    @Value("${jwt.expires_in}")
    private int EXPIRED_IN;
    @Value("${jwt.header}")
    private String AUTH_HEADER;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public int getExpiredIn() {
        return this.EXPIRED_IN;
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            username = this.getAllClaimsFromToken(token).getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private Date createExpirationDate() {
        long expiresIn = EXPIRED_IN;
        return new Date(System.currentTimeMillis() + expiresIn * 1000);
    }

    public String generateToken(String username) {
        return Jwts.builder().setIssuer(APP_NAME).setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(this.createExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
    }

    private String refreshToken(String token) {
        String newToken;
        Date currentDate = new Date(System.currentTimeMillis());
        try {
            newToken = Jwts.builder()
                    .setClaims(this.getAllClaimsFromToken(token))
                    .setIssuedAt(currentDate)
                    .setExpiration(createExpirationDate())
                    .signWith(SIGNATURE_ALGORITHM, SECRET)
                    .compact();
        } catch (Exception e) {
            newToken = null;
        }
        return newToken;
    }

    public Date getIssuedAtDateFromToken(String token) {
        Date issued;
        try {
            issued = this.getAllClaimsFromToken(token).getIssuedAt();
        } catch (Exception e) {
            issued = null;
        }
        return issued;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username != null && username.equals(userDetails.getUsername());
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }

    public String getToken(HttpServletRequest request) {
        String authHeader = getAuthHeaderFromHeader(request);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

}
