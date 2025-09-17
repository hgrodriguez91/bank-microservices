package com.devsu.client.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientRequestDto {
    @NotBlank(message = "The client name is required")
    private String name;
    @NotBlank(message = "The client gender is required")
    private String gender;
    @Min(value = 18, message = "The client age must be more than 18 years")
    @NotNull(message = "The client age is required")
    private Integer age;
    @NotNull(message = "the client identification is required")
    private String identification;
    private String address;
    @NotBlank(message = "The client phone is required")
    private String phone;
    @NotBlank(message = "The client phone is required")
    private String password;
}
