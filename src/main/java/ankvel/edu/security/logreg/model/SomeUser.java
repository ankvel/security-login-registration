package ankvel.edu.security.logreg.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "some_user")
@SequenceGenerator(name = "some_user_seq", sequenceName = "some_user_seq")
public class SomeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "some_user_seq")
    private Long id;

    private String name;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<SomeRole> roles;

    public SomeUser() {
    }

    public boolean isAnonymous() {
        return "None".equals(getEmail());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<SomeRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<SomeRole> roles) {
        this.roles = roles;
    }
}
