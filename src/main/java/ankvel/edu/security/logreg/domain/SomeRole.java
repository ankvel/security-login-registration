package ankvel.edu.security.logreg.domain;

import javax.persistence.*;

@Entity
@Table(name = "some_role")
@SequenceGenerator(name = "some_role_seq", sequenceName = "some_role_seq", allocationSize = 25)
public class SomeRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "some_role_seq")
    private Long id;

    private String name;

    public SomeRole() {
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
}
