package mil.army.moda.springexample.person;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PersonServiceSecurityMockTest.TestConfig.class)
class PersonServiceSecurityMockTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Test
    @WithMockPerson(role = "TEACHER", assignments = "FACULTY")
    void allowsTeacherWithAssignment() {
        when(personRepository.findAssignmentByType()).thenReturn(new Person());

        assertThatCode(() -> personService.getCurrentPersonAndAssignment("FACULTY"))
                .doesNotThrowAnyException();
    }

    @Test
    @WithMockPerson(role = "STUDENT", assignments = "FACULTY")
    void deniesUserWithoutTeacherRole() {
        assertThatThrownBy(() -> personService.getCurrentPersonAndAssignment("FACULTY"))
                .isInstanceOf(AccessDeniedException.class);
    }

    @Test
    @WithMockPerson(role = "TEACHER", assignments = "OTHER")
    void deniesUserWithoutRequiredAssignment() {
        assertThatThrownBy(() -> personService.getCurrentPersonAndAssignment("FACULTY"))
                .isInstanceOf(AccessDeniedException.class);
    }

    @EnableMethodSecurity
    @Configuration
    static class TestConfig {

        @Bean
        PersonService personService(PersonRepository personRepository) {
            return new PersonService(personRepository);
        }

        @Bean
        PersonRepository personRepository(PersonRepository personRepository) {
            return mock(PersonRepository.class);
        }
    }
}