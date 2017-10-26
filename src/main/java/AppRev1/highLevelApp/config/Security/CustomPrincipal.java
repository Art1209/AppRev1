package AppRev1.highLevelApp.config.Security;

import AppRev1.highLevelApp.persistence.entity.Token;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by aalbutov on 17.10.2017.
 */
public class CustomPrincipal extends User {
    private List<Token> tokens;
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {

        return id;
    }

    public CustomPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities, long id) {
        super(username, password, authorities);
        this.id = id;
    }

    public CustomPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities, List<Token> tokens) {
        super(username, password, authorities);
        this.tokens = tokens;
        this.id = id;
    }

    public CustomPrincipal(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, long id) {

        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
    }

    public CustomPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomPrincipal(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
//todo delete
    @Override
    public String toString() {
        return "id: "+id;
    }
}
