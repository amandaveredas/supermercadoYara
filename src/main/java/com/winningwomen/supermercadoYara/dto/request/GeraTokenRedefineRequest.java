package com.winningwomen.supermercadoYara.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
public class GeraTokenRedefineRequest {

    @NotBlank(message = "O campo email não pode ser nulo.")
    private String email;


}
