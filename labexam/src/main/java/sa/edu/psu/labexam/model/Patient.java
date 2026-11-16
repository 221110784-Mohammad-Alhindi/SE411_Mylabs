package sa.edu.psu.labexam.model;

import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record Patient(
        @Id Integer id,
        @NotBlank String name,
        String city,
        @Positive Integer age
) {}