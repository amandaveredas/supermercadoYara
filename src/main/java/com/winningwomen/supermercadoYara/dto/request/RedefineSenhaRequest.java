package com.winningwomen.supermercadoYara.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class RedefineSenhaRequest {

    @NotBlank(message = "O campo email não pode ser nulo.")
    private String email;
    @NotNull(message = "O campo token não pode ser nulo.")
    private Long token;
    @NotNull(message = "O campo nova senha não pode ser nulo.")
    private String novaSenha;

}
