package mil.army.moda.springexample.exampleSpecification;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }


    public List<Book> getAllBooksWihTitleOf(String title) {
        Specification<Book> spec = Specification.where(BookSpecs.isAvailable(true))
            .and(BookSpecs.title(title));

        return bookRepository.findAll(spec);
    }

    public Book getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found: " + id));

        if (!book.getAvailable()) {
            throw new EntityNotFoundException("Book not available: " + id);
        }

        return book;
    }
}
