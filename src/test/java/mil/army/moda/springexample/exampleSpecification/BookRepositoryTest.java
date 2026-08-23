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
        Book availableBook1 = new Book();
        availableBook1.setAvailable(true);

        Book availableBook2 = new Book();
        availableBook2.setAvailable(true);

        Book unavailableBook = new Book();
        unavailableBook.setAvailable(false);

        bookRepository.saveAll(List.of(
                availableBook1,
                availableBook2,
                unavailableBook
        ));

        // Act
        Specification<Book> spec = BookSpecs.isAvailable(true);

        List<Book> result = bookRepository.findAll(spec);

        // Assert
        assertThat(result).hasSize(2);
        //assertThat(result).allMatch(Book::available);
    }
}