package AppRev1.highLevelApp.config.Security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by aalbutov on 26.10.2017.
 */
public class TokenProvider implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        String text=authentication.getPrincipal().toString()+authentication.getAuthorities().toString();
        String encripted = encryptor.encryptPassword(text);
        response.addHeader(HttpHeaders.SET_COOKIE,encripted);

    }
}
