package mil.army.moda.springexample.exampleDto;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.jspecify.annotations.NonNull;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    private String name;

    @Nonnull
    private String description;

    @Nonnull
    private Double price;

    @Nonnull
    private Integer stock_number;

    @Nonnull
    private Boolean isAvailable;

    public Product() {
    }

    public Product(@Nonnull String name, @Nonnull String description, @Nonnull Double price, @Nonnull Integer stock_number, @Nonnull Boolean isAvailable) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock_number = stock_number;
        this.isAvailable = isAvailable;
    }

    public Long getId() { return id; }
    public @NonNull String getName() { return name; }
    public void setName(@NonNull String name) { this.name = name; }
    public @NonNull String getDescription() { return description; }
    public void setDescription(@NonNull String description) { this.description = description; }
    public @NonNull Double getPrice() { return price; }
    public void setPrice(@NonNull Double price) { this.price = price; }
    public Integer getStockNumber() { return stock_number; }
    public void setStockNumber(@Nonnull Integer stock_number) { this.stock_number = stock_number; }
    public @NonNull Boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(@NonNull Boolean isAvailable) { this.isAvailable = isAvailable; }
}
