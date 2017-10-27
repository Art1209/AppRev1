package AppRev1.highLevelApp.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "roleId", length = 6, nullable = false)
    private Long roleId;

    @Column(name = "role")
    private String role;

    @Getter
    @Setter
    @JsonIgnore
    @ManyToMany(targetEntity = Person.class,
            cascade = CascadeType.MERGE, mappedBy="rolesList")
    private List<Person> personList;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Long getId() {
        return roleId;
    }
    public String getRole() {
        return role;
    }

    public void setId(Long id) {
        this.roleId = id;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        Role other = (Role)obj;
        return this.getId()==other.getId();
    }
    @Override
    public int hashCode(){return (int)(getId()%Integer.MAX_VALUE);}
}

