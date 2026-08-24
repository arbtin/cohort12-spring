package mil.army.moda.springexample.exampleSpecification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldFindOnlyAvailableBooks() {
        // Arrange
        Book availableBook = new Book();
        availableBook.setAvailable(true);

        Book unAvailableBook = new Book();
        unAvailableBook.setAvailable(false);

        bookRepository.saveAll(List.of(
                availableBook,
                unAvailableBook
        ));

        // Act
        List<Book> result = bookRepository.findAll(BookSpecification.isAvailable(true));

        // Assert
        assertThat(result).hasSize(1);
    }
}