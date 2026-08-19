package mil.army.moda.springexample.exampleDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductResponse(
        @NotBlank Long id,
        @NotBlank String name,
        @NotBlank String description,
        @NotNull Double price,
        @NotNull Integer stock_number,
        Boolean isAvailable
) {
    public static ProductResponse fron(Product response) {
        return new ProductResponse(
                response.getId(),
                response.getName(),
                response.getDescription(),
                response.getPrice(),
                response.getStockNumber(),
                response.getIsAvailable());
    }

}
