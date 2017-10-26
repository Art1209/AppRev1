package AppRev1.highLevelApp.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    private String token;

    @ManyToOne(targetEntity = Person.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "personId")
    @Getter
    @Setter
    private Person personId;
}
