package edu.psu.se411.lab13.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Digits;

import org.springframework.data.annotation.Id;

public record Product(
        @Id
        @NotNull
        @Positive
        Integer id,

        @NotBlank
        String name,

        String description,

        @NotNull
        @Digits(integer = 8, fraction = 2)
        BigDecimal price
) {}