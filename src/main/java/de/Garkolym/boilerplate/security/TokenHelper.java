package de.Garkolym.boilerplate.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

}
