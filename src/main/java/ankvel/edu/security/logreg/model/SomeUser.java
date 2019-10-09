package ankvel.edu.security.logreg.model;

import java.util.Collection;

public class SomeUser {

    private Long id;

    private String name;

    private String email;

    private String password;

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
