package ankvel.edu.security.logreg.security;

import ankvel.edu.security.logreg.model.SomeRole;
import ankvel.edu.security.logreg.model.SomeUser;
import ankvel.edu.security.logreg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        SomeUser someUser = userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("No user found with username: " + email));

        return new User(someUser.getEmail(), someUser.getPassword(),
                true, true, true, true,
                getGrantedAuthorities(someUser.getRoles()));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Collection<SomeRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (SomeRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
