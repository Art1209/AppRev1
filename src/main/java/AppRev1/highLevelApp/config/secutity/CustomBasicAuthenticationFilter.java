package AppRev1.highLevelApp.config.secutity;

import AppRev1.highLevelApp.persistence.entity.Person;
import AppRev1.highLevelApp.persistence.entity.Token;
import AppRev1.highLevelApp.persistence.service.PersonService;
import AppRev1.highLevelApp.persistence.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by aalbutov on 27.10.2017.
 */
@Component
public class CustomBasicAuthenticationFilter extends BasicAuthenticationFilter implements AuthenticationSuccessHandler {


    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    PersonService personService;



    @Autowired
    public CustomBasicAuthenticationFilter(final AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, Authentication authResult) {
        Person person = personService.getPerson(((CustomPrincipal)authResult.getPrincipal()).getId());
        Token token = null;
        List<Token> tokens;
        if ((tokens = person.getTokenList())!=null){
            for (Token token1 : tokens){
                if ((token1.getExpires().getTime()-System.currentTimeMillis())>0){
                    token = token1;
                    break;
                }

            }
        }
        if (null==token)token = tokenProvider.generateToken(person.getId());
        //send token in the response
        response.setHeader(HttpHeaders.AUTHORIZATION , token.getToken());
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        onSuccessfulAuthentication(request, response, authentication);
    }
}
