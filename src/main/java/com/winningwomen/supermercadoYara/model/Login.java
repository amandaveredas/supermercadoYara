package com.winningwomen.supermercadoYara.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="login")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Campo usuário não pode ser nulo.")
    @ManyToOne
    private Usuario usuario;
    @NotNull(message = "Campo hora do login não pode ser nulo.")
    private LocalDateTime horaLogin;
    private LocalDateTime horaLogout;

}
