package AppRev1.highLevelApp.config.secutity;

import AppRev1.highLevelApp.persistence.entity.Person;
import AppRev1.highLevelApp.persistence.entity.Token;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by aalbutov on 26.10.2017.
 */
public class TokenProvider{

    @Autowired
    StrongTextEncryptor encryptor;

    @Autowired
    PasswordEncoder encoder;

    public Token generateToken(Person person){
        Date expires = new Date(System.currentTimeMillis()+1209600000l); // 2 weeks
        String tokenString = encryptor.encrypt(person.getLogin()+":"+encoder.encode(person.getPassword()));
        Token token = new Token(tokenString, expires, person );
        return token;
    }
}
