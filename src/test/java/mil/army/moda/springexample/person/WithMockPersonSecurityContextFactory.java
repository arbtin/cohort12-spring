package mil.army.moda.springexample.person;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

public class WithMockPersonSecurityContextFactory implements WithSecurityContextFactory<WithMockPerson> {
    @Override
    public SecurityContext createSecurityContext(WithMockPerson annotation) {
        Person person = new Person();
        person.setId(UUID.randomUUID());
        person.setUserName("Fred");
        person.setRoles(Set.of(new Role(annotation.role())));
        person.setAssignments(Arrays.stream(annotation.assignments()).map(Assignment::new).toList());

        PersonPrincipal principal = new PersonPrincipal(person);
        var auth = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        return context;
    }
}