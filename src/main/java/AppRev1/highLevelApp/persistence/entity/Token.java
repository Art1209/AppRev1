package AppRev1.highLevelApp.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by aalbutov on 26.10.2017.
 */

@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "tokenId", length = 6, nullable = false)
    private Long id;

    @Column(name = "token")
    @Getter
    @Setter
    private String token;

    @Column(name = "expires")
    @Getter
    @Setter
    private Date expires;

    @ManyToOne(targetEntity = Person.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "personId")
    @Getter
    @Setter
    private Person personId;

    public Token(String token) {
        this.token = token;
    }

    public Token(String token, Person personId) {
        this.token = token;
        this.personId = personId;
    }

    public Token(String token, Date expires, Person personId) {
        this.token = token;
        this.expires = expires;
        this.personId = personId;
    }

    public Token() {
    }
}
