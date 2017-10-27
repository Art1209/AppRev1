package AppRev1.highLevelApp.config.secutity.old;

import AppRev1.highLevelApp.config.secutity.CustomPrincipal;
import AppRev1.highLevelApp.persistence.entity.Token;
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
        String name = "admin";
        SimpleGrantedAuthority user1 = new SimpleGrantedAuthority("admin");
        SimpleGrantedAuthority user2 = new SimpleGrantedAuthority("user");
        Collection<? extends GrantedAuthority> auths = Arrays.asList(user1,user2);
        CustomPrincipal user = new CustomPrincipal(name, "password2",auths, Arrays.asList(new Token[]{new Token("devtoken")}));
        return user;
    }
}
