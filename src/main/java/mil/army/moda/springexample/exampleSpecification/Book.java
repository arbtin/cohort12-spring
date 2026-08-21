package mil.army.moda.springexample.exampleSpecification;

import jakarta.annotation.Nonnull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    private String title;

    @Nonnull
    private String description;

    @Nonnull
    private Double price;

    @Nonnull
    private String isbn;

    @Nonnull
    private Boolean available;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nonnull
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nonnull String title) {
        this.title = title;
    }

    @Nonnull
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nonnull String description) {
        this.description = description;
    }

    @Nonnull
    public Double getPrice() {
        return price;
    }

    public void setPrice(@Nonnull Double price) {
        this.price = price;
    }

    @Nonnull
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(@Nonnull String isbn) {
        this.isbn = isbn;
    }

    @Nonnull
    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(@Nonnull Boolean available) {
        this.available = available;
    }
}
