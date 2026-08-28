package mil.army.moda.springexample.person;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PreAuthorize("hasRole('TEACHERR')")
    public Person getCurrentPersonAndAssignment(String assignmentType) throws AccessDeniedException {
        PersonPrincipal principal =
                (PersonPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean hasAssignment = principal.getAssignments().stream()
                .anyMatch(a -> a.getType().equals(assignmentType));

        if (!hasAssignment) {
            throw new AccessDeniedException("Missing required assignment: " + assignmentType);
        }

        return personRepository.findAssignmentByType();
    }

}
