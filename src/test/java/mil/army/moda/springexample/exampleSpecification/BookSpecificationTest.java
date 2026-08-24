package mil.army.moda.springexample.exampleSpecification;

import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookSpecificationTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void shouldReturnOnlyAvailableBooks() {
        // Arrange
        Book hobbitNotAvailable = new Book("Hobbit", "by JRR Tolkin", 10.00, "ISBN1234", false);
        Book leanAvailable = new Book("Lean Startup", "by Author", 20.00, "ISBN5678", true);
        Book xpAvailable = new Book("XP Programming", "by Author", 25.00, "ISBN91011", true);

        testEntityManager.persist(hobbitNotAvailable);
        testEntityManager.persist(leanAvailable);
        testEntityManager.persist(xpAvailable);
        testEntityManager.flush();

        // Act
        List<Book> result = bookRepository.findAll(BookSpecification.isAvailable(true));

        // Assert
        assertThat(result)
                .hasSize(2)
                .extracting(Book::getTitle)
                .containsExactly("Lean Startup", "XP Programming");
    }

    @Test
    void shouldReturnOnlyUnavailableBooks() {
        // Arrange
        Book hobbitNotAvailable = new Book("The Hobbit", "by JRR Tolkin", 10.00, "ISBN1234", false);
        Book leanAvailable = new Book("Lean Startup", "by Author", 20.00, "ISBN5678", true);
        Book xpAvailable = new Book("XP Programming", "by Author", 25.00, "ISBN91011", true);

        testEntityManager.persist(hobbitNotAvailable);
        testEntityManager.persist(leanAvailable);
        testEntityManager.persist(xpAvailable);
        testEntityManager.flush();

        // Act
        List<Book> result = bookRepository.findAll(BookSpecification.isAvailable(false));

        // Assert
        assertThat(result)
                .hasSize(1)
                .extracting(Book::getTitle)
                .containsExactly("The Hobbit");
    }
}