package AppRev1.highLevelApp.config.secutity;

import AppRev1.highLevelApp.persistence.entity.Person;
import AppRev1.highLevelApp.persistence.entity.Token;
import AppRev1.highLevelApp.persistence.service.PersonService;
import AppRev1.highLevelApp.persistence.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by aalbutov on 27.10.2017.
 */
@Component
public class CustomBasicAuthenticationFilter extends BasicAuthenticationFilter {


    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    PersonService personService;

    @Autowired
    TokenService tokenService;

    @Autowired
    public CustomBasicAuthenticationFilter(final AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, Authentication authResult) {
        Person person = personService.getPerson(((CustomPrincipal)authResult.getPrincipal()).getId());

        Token token = tokenProvider.generateToken(person);
        tokenService.addToken(token);
        //send token in the response
        response.setHeader("X-AUTH-TOKEN" , token.getToken());


    }

}
