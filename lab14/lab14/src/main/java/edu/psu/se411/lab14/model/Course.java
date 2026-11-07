package edu.psu.se411.lab14.model;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record Course(

        @Id
        @Min(0)
        Long id,

        @NotBlank
        String code,

        @NotBlank
        @Size(min = 2)
        String name,

        String description,

        @Min(1)
        @Max(10)
        Integer credits

) {}