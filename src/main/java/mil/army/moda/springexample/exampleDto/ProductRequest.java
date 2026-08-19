package mil.army.moda.springexample.exampleDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequest (
    @NotBlank String name,
    @NotBlank String description,
    @NotNull Double price,
    @NotNull Integer stock_number,
    Boolean isAvailable
) {}
