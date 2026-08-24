package mil.army.moda.springexample.exampleSpecification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @Test
    void shouldFindAvailableUsingGatAvailableBooksTrue() {
        //Arrange
        Book availableBook = new Book("Hobbit", "by JRR Tolkin", 10.00, "ISBN1234", true);
        given(bookRepository.findAll(any(Specification.class))).willReturn(List.of(availableBook));

        // Act
        List<Book> result = bookService.getAvailableBooks(true);

        // Assert
        assertThat(result).containsExactly(availableBook);
        verify(bookRepository).findAll(any(Specification.class));
    }

    @Test
    void shouldFindAvailableUsingGatAvailableBooksFalse() {
        //Arrange
        Book notAvailableBook = new Book("Hobbit", "by JRR Tolkin", 10.00, "ISBN1234", false);
        Book availableBook = new Book("Lean Startup", "by Author", 20.00, "ISBN5678", true);

        given(bookRepository.findAll(any(Specification.class))).willReturn(List.of(notAvailableBook));

        // Act
        List<Book> result = bookService.getAvailableBooks(false);

        // Assert
        assertThat(result).containsExactly(notAvailableBook);
        verify(bookRepository).findAll(any(Specification.class));
    }

    @Test
    void shouldGetBooksByTitleWhereIs() {
        //Arrange
//        given(bookRepository.findAll(any(Specification.class))).willReturn(library);
//
//        // Act
//        List<Book> result = bookService.getAllBooksHavingTitleOf("Lean Startup");
//
//        // Assert
//        assertThat(result).isEqualTo(library);
//        assertThat(result.size()).isEqualTo(3);
//        //assertEquals();
//        verify(bookRepository, only()).findAll();
    }

    @Test
    void validateGetAvailableBooksBuildsCorrectSpecification() {
        ArgumentCaptor<Specification<Book>> captor = ArgumentCaptor.forClass(Specification.class);
        given(bookRepository.findAll(captor.capture())).willReturn(List.of());

        bookService.getAvailableBooks(true);

        Root<Book> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        Path<Object> path = mock(Path.class);

        given(root.get("available")).willReturn(path);
        captor.getValue().toPredicate(root, query, cb);

        verify(cb).equal(path, true);
    }
}