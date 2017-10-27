package AppRev1.highLevelApp.config.secutity;

import AppRev1.highLevelApp.persistence.entity.Person;
import AppRev1.highLevelApp.persistence.entity.Role;
import AppRev1.highLevelApp.persistence.entity.Token;
import AppRev1.highLevelApp.persistence.service.PersonService;
import AppRev1.highLevelApp.persistence.service.RoleService;
import AppRev1.highLevelApp.persistence.service.TokenService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PersonService ps;

    @Autowired
    TokenService ts;

    @Autowired
    RoleService rs;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);
        Person person = ps.getPerson(username);
        if (person==null){
            log.info("person service return null for");
            throw new UsernameNotFoundException("from userDetailsService");
        }
        List<GrantedAuthority> auths = new ArrayList<>();
        for (Role role : person.getRolesList()){
            log.info(role.getId());
            auths.add(new SimpleGrantedAuthority(role.getRole()));
        }
        log.info(person.getLogin()+" "+
                person.getPassword()+" "+
                person.getRolesList());

        List<Token> tokens = ts.getTokenByPersonId(person);
        if (tokens!=null){
            for (Token token:tokens){
                if (token.getExpires().getTime()<System.currentTimeMillis()){
                    ts.deleteToken(token);
                }
            }
        }

        UserDetails userDetails = new CustomPrincipal(person.getLogin(),
                person.getPassword(),auths, ts.getTokenByPersonId(person), person.getId());
        return userDetails;
    }
}

