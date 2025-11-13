package com.foodlab.foodlab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Genera constructor con todos los args
public class LoginResponseDTO {
    private String token;
}

