package com.winningwomen.supermercadoYara.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="recuperacao")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RedefinicaoSenha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Campo usuário não pode ser nulo.")
    @ManyToOne
    private Usuario usuario;
    @NotNull(message = "Campo hora da solicitação não pode ser nulo.")
    private LocalDateTime horaSolicitacao;
    private LocalDateTime horaExpiracao;
}
