package com.foodlab.foodlab.dto;

import com.foodlab.foodlab.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Genera constructor con todos los args
public class LoginResponseDTO {
    private String token;
    private Usuario usuario; 


}

