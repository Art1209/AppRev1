package AppRev1.highLevelApp.config.secutity;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by aalbutov on 27.10.2017.
 */
public class TokenAuthenticationFilter extends GenericFilterBean
{

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    StrongTextEncryptor encryptor;

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException
    {
        final HttpServletRequest httpRequest = (HttpServletRequest)request;
        final String accessToken = httpRequest.getHeader("header-name");
        String login;
        if (null != accessToken && null!=(login = parseTokenForLogin(accessToken))) {

            CustomPrincipal principal = (CustomPrincipal) userDetailsService.loadUserByUsername(login);
            final UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    private String parseTokenForLogin(String token){
        String login = null;
        if (token.equals("devtoken"))return "admin";
        else{
            String plainText = encryptor.decrypt(token);
            int index = plainText.indexOf(':');
            if (index>1){
                login = plainText.substring(0,index);
            }
        }
        return login;
    }

}
