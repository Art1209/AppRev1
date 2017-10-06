package AppRev1.highLevelApp.config;

import AppRev1.highLevelApp.persistence.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TokenAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            if (authentication instanceof TokenAuthentication) {
                TokenAuthentication readyTokenAuthentication = processAuthentication((TokenAuthentication) authentication);
                return readyTokenAuthentication;
            } else {
                authentication.setAuthenticated(false);
                return authentication;
            }
        } catch (Exception ex) {
            if(ex instanceof AuthenticationServiceException)
                throw ex;
        } return authentication;
    }

    private TokenAuthentication processAuthentication(TokenAuthentication authentication) throws AuthenticationException {
        String token = authentication.getToken();
        String key = "key123";
        DefaultClaims claims;
        try {
            claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        } catch (Exception ex) {
            throw new AuthenticationServiceException("Token corrupted");
        }
        if (claims.get("TOKEN_EXPIRATION_DATE", Long.class) == null)
            throw new AuthenticationServiceException("Invalid token");
        Date expiredDate = new Date(claims.get("TOKEN_EXPIRATION_DATE", Long.class));
        if (expiredDate.after(new Date()))
            return buildFullTokenAuthentication(authentication, claims);
        else
            throw new AuthenticationServiceException("Token expired date error");
    }

    private TokenAuthentication buildFullTokenAuthentication(TokenAuthentication authentication, DefaultClaims claims) {
        User user = (User) userDetailsService.loadUserByUsername(claims.get("USERNAME", String.class));
        if (user.isEnabled()) {
            Collection<GrantedAuthority> authorities = user.getAuthorities();
            TokenAuthentication fullTokenAuthentication =
                    new TokenAuthentication(authentication.getToken(), authorities, true, user);
            return fullTokenAuthentication;
        } else {
            throw new AuthenticationServiceException("User disabled");
        }
    }
}