package AppRev1.highLevelApp.config.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collection;

/**
 * Created by aalbutov on 17.10.2017.
 */
public class UserDetailsWrapper implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

        @Autowired
        private UserDetailsService userDetailsService;

        @Override
        public UserDetails loadUserDetails(final PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
            String name = parseTokenForLogin(token);
            String password = parseTokenForPassword(token);
            CustomPrincipal principal = (CustomPrincipal)userDetailsService.loadUserByUsername(name);
            if (principal!=null&&principal.getPassword().equals(password)){
                Collection<? extends GrantedAuthority> auths = principal.getAuthorities();
                CustomPrincipal user = new CustomPrincipal(name, password, principal.isEnabled(),
                        principal.isAccountNonExpired(),principal.isCredentialsNonExpired(),
                        principal.isAccountNonLocked(),auths, 4l);
                return user;
            }
            return null;
        }

        private String parseTokenForLogin(PreAuthenticatedAuthenticationToken token){
            //todo add parse logic
            return "login";
        }

        private String parseTokenForPassword(PreAuthenticatedAuthenticationToken token){
            //todo add parse logic
            return "password";
        }
}
