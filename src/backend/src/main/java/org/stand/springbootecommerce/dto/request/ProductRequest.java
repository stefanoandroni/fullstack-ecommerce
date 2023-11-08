package org.stand.springbootecommerce.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
public class ProductRequest {
    @NotBlank
    @Size(max = 80)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String description;

    @NotBlank
    @Size(max = 80)
    private String shortDescription;

    @NotNull
    @PositiveOrZero
    private int quantity = 0;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotBlank
    @Size(max = 255)
    // @Pattern(regexp = "^https?://.*\\.(png|jpg|jpeg)$")
    private String image;

    @NotNull
    private Long category;
}