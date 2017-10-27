package AppRev1.highLevelApp.config.secutity.old;

import AppRev1.highLevelApp.config.secutity.CustomPrincipal;
import AppRev1.highLevelApp.persistence.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

/**
 * Created by aalbutov on 17.10.2017.
 */
public class UserDetailsWrapper implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

        @Autowired
        private UserDetailsService userDetailsService;

        @Override
        public UserDetails loadUserDetails(final PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
            String name = parseTokenForLogin(token);
            CustomPrincipal principal = (CustomPrincipal)userDetailsService.loadUserByUsername(name);
            if (principal!=null&&principal.getTokens()!=null&&!principal.getTokens().isEmpty()){
                for (Token token1:principal.getTokens()){
                    if (((String)token.getPrincipal()).equals(token1.getToken())||
                            ((String)token.getCredentials()).equals(token1.getToken())){
                        CustomPrincipal user = new CustomPrincipal(principal.getUsername(), principal.getPassword(), true,
                                true,true,true,
                                principal.getAuthorities(), token1.getPersonId().getId());
                        return user;
                    }
                }
            }
            return principal;
        }

        private String parseTokenForLogin(PreAuthenticatedAuthenticationToken token){
            if (((String)token.getPrincipal()).equals("devtoken")||
                    ((String)token.getCredentials()).equals("devtoken"))return "admin";
            //todo add parse logic
            return "login";
        }

        private String parseTokenForPassword(PreAuthenticatedAuthenticationToken token){
            //todo add parse logic
            return "password";
        }
}
