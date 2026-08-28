package mil.army.moda.springexample.person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepositiory extends JpaRepository<Person, Long> {
    Person findAssignmentByType();
}
