package mil.army.moda.springexample.person;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonServiceAssignmentLogicTest {

    private PersonService personService;
    private PersonRepositiory personRepository;

    @BeforeEach
    void setUp() {
        personRepository = mock(PersonRepositiory.class);
        personService = new PersonService(personRepository);
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    private void authenticateAs(String roleName, String... assignmentTypes) {
        Person person = new Person();
        person.setId(UUID.randomUUID());
        person.setUserName("jdoe");
        person.setRoles(Set.of(new Role(roleName)));
        person.setAssignments(Arrays.stream(assignmentTypes).map(Assignment::new).toList());

        PersonPrincipal principal = new PersonPrincipal(person);
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    void allowsUserWithMatchingAssignment() throws java.nio.file.AccessDeniedException {
        authenticateAs("TEACHER", "FACULTY", "OTHER");
        Person expected = new Person();
        when(personRepository.findAssignmentByType()).thenReturn(expected);

        Person result = personService.getCurrentPersonAndAssignment("FACULTY");

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void deniesUserWithoutRequiredAssignment() {
        authenticateAs("TEACHER", "OTHER");

        assertThatThrownBy(() -> personService.getCurrentPersonAndAssignment("FACULTY"))
                .isInstanceOf(AccessDeniedException.class);
    }
}