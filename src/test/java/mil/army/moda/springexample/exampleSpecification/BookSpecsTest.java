package mil.army.moda.springexample.exampleSpecification;

import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookSpecsTest {

    @Mock
    private Root<Book> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder cb;

    @Mock
    private Path<Boolean> path;

    @Mock
    private Predicate predicate;

    @Test
    void shouldCreateSpecificationForAvailableBooks() {
        // Arrange
//        when(root.get("available")).thenReturn(path);
        when(cb.equal(path, true)).thenReturn(predicate);

        Specification<Book> specification = BookSpecs.isAvailable(true);

        // Act
        Predicate result = specification.toPredicate(root, query, cb);

        // Assert
        assertThat(result).isSameAs(predicate);

        verify(root).get("available");
        verify(cb).equal(path, true);
    }

    @Test
    void shouldCreateUnavailableSpecification() {
        // Arrange
//        when(root.get("available")).thenReturn(path);
        when(cb.equal(path, false)).thenReturn(predicate);

        Specification<Book> specification = BookSpecs.isAvailable(false);

        // Act
        Predicate result = specification.toPredicate(root, query, cb);

        // Assert
        assertThat(result).isSameAs(predicate);

        verify(root).get("available");
        verify(cb).equal(path, false);
    }
}