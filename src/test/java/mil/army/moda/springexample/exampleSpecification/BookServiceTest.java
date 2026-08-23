package mil.army.moda.springexample.exampleSpecification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    Book hobbit;
    Book lean;
    Book xp;

    List<Book> library = new ArrayList<>();

    @BeforeEach
    void setUp() {
        hobbit = new Book("Hobbit", "by JRR Tolkin", 10.00, "ISBN1234", false);
        lean = new Book("Lean Startup", "by Author", 20.00, "ISBN5678", true);
        xp = new Book("XP Programming", "by Author", 25.00, "ISBN91011", true);

        hobbit.setId(1L);

        library.add(hobbit);
        library.add(lean);
        library.add(xp);
    }

    @Test
    void shouldGetBooksByTitleWhereIs() {
        //Arrange
        when(bookRepository.findAll(any(Specification.class))).thenReturn(library);

        // Act
        List<Book> result = bookService.getAllBooksHavingTitleOf("Lean Startup");

        // Assert
        assertThat(result).isEqualTo(library);
        assertThat(result.size()).isEqualTo(3);
        //assertEquals();
        verify(bookRepository, only()).findAll();
    }

    @Test
    void shouldGatAllAvailableBooks() {
        //Arrange
        when(bookRepository.findAll(any(Specification.class))).thenReturn(library);

        // Act
        List<Book> result = bookService.getAllAvailableBooks();

        // Assert
        assertThat(result).isEqualTo(library);
        assertThat(result.size()).isEqualTo(3);

        verify(bookRepository).findAll(any(Specification.class));
    }
}