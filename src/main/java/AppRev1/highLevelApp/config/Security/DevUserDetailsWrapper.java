package AppRev1.highLevelApp.config.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by aalbutov on 17.10.2017.
 */
public class DevUserDetailsWrapper implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Override
    public UserDetails loadUserDetails(final PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        if ("user".equals((String)token.getPrincipal()))throw new UsernameNotFoundException("wrong token");
        String name = "ADMIN";
        SimpleGrantedAuthority user1 = new SimpleGrantedAuthority("ROLE_1");
        SimpleGrantedAuthority user2 = new SimpleGrantedAuthority("ROLE_2");
        Collection<? extends GrantedAuthority> auths = Arrays.asList(user1,user2);
        CustomPrincipal user = new CustomPrincipal(name, "password", true,true,
                true, true,auths, 4l);
        return user;
    }
}
