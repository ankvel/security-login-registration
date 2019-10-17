package ankvel.edu.security.logreg.domain;

import javax.persistence.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "user_verify")
@SequenceGenerator(name = "user_verify_seq", sequenceName = "user_verify_seq", allocationSize = 25)
public class UserVerifyToken {

    private static final long DEFAULT_EXPIRE_DAYS = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "some_user_seq")
    private Long id;

    private String token;

    @OneToOne(targetEntity = SomeUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private SomeUser user;

    private Instant createDate;

    private Instant expireDate;

    public UserVerifyToken() {
    }

    public UserVerifyToken(String token, SomeUser user) {
        this.token = token;
        this.user = user;
        createDate = Instant.now();
        expireDate = createDate.plus(DEFAULT_EXPIRE_DAYS, ChronoUnit.DAYS);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SomeUser getUser() {
        return user;
    }

    public void setUser(SomeUser user) {
        this.user = user;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Instant expireDate) {
        this.expireDate = expireDate;
    }
}
