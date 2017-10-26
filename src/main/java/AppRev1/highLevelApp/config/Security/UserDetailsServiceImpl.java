package AppRev1.highLevelApp.config.Security;

import AppRev1.highLevelApp.persistence.entity.Person;
import AppRev1.highLevelApp.persistence.entity.Role;
import AppRev1.highLevelApp.persistence.service.PersonService;
import AppRev1.highLevelApp.persistence.service.RoleService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
    RoleService rs;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = ps.getPerson(username);
        if (person==null)throw new UsernameNotFoundException("from userDetailsService");
        List<GrantedAuthority> auths = new ArrayList<>();
        for (Role role : person.getRolesList()){
            auths.add(new SimpleGrantedAuthority(role.getRole()));
        }
        log.info(person.getLogin()+" "+
                person.getPassword()+" "+
                person.getRolesList());
        UserDetails userDetails = new CustomPrincipal(person.getLogin(),
                person.getPassword(),auths, ps.getTokens(person.getId()));

        return userDetails;
    }
}

