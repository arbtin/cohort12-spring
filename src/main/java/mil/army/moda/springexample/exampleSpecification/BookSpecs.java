package mil.army.moda.springexample.exampleSpecification;

import org.springframework.data.jpa.domain.Specification;

public class BookSpecs {

    public static Specification<Book> title(String title) {
        return (root, query, cb) ->
                title == null ? cb.conjunction() : cb.equal(root.get("title"), title);
    }

    public static Specification<Book> isAvailable(boolean available) {
        return (root, query, cb) -> cb.equal(root.get("isAvailable"), available);
    }
}
