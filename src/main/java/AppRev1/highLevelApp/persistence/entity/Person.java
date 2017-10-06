package AppRev1.highLevelApp.persistence.entity;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "personId", length = 6, nullable = false)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(targetEntity=Role.class, cascade = CascadeType.MERGE)
    @JoinTable(
            name="PERSON_ROLE",
            joinColumns=@JoinColumn(name="PERSON_ID", referencedColumnName="personId"),
            inverseJoinColumns=@JoinColumn(name="ROLE_ID", referencedColumnName="roleId"))
    private List<Role> rolesList=new ArrayList<>();

    public Person(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Person() {
    }

    public void setRolesList(List<Role> rolesList) {
        this.rolesList = rolesList;
    }

    public List<Role> getRolesList() {

        return rolesList;
    }
    public Long getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getName() {
        return login;
    }
    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setName(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        Person other = (Person)obj;
        return this.getId()==other.getId();
    }
    @Override
    public int hashCode()
    {
        int result = (int)(getId()%Integer.MAX_VALUE);
        return result;
    }
}

