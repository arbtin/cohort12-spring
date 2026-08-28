package mil.army.moda.springexample.person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


import java.nio.file.AccessDeniedException;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @MockitoBean
    private PersonRepository personRepository;
    @Test
    @WithMockPerson(role = "TEACHER", assignments = "FACULTY")
    void allowsTeacherWithAssignment() {
        assertThatCode(() -> personService.getCurrentPersonAndAssignment("FACULTY"))
                .doesNotThrowAnyException();
    }

    @Test
    @WithMockPerson(role = "STUDENT", assignments = "FACULTY")
    void deniesUserWithoutTeacherRole() {
        assertThatThrownBy(() -> personService.getCurrentPersonAndAssignment("FACULTY"))
                .isInstanceOf(AccessDeniedException.class);
    }
}