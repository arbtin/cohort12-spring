package mil.army.moda.springexample.person;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class PersonPrincipal implements UserDetails {
    private final Person person;

    public PersonPrincipal(Person person) { this.person = person; }

    public List<Assignment> getAssignments() { return person.getAssignments(); }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return person.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
                .toList();
    }

    @Override public String getUsername() { return person.getUserName(); }
    @Override public String getPassword() { return null; }
    // isAccountNonExpired/Locked, isCredentialsNonExpired, isEnabled default to true
    // via interface defaults in Spring Security 6+, so no need to override them
}