package com.winningwomen.supermercadoYara.model;

import java.time.LocalDate;

import javax.persistence.*;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="usuario")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(nullable = false, length = 15)
    private String user_name;

    @Column(nullable = false, length = 50)
	private String nome;

    @Column(nullable = false, length = 50)
	private String sobrenome;

    @Column(nullable = false, length = 150)
	private String email;

    @Column(nullable = false)
	private String senha;

    @Column(name = "data_criacao", updatable = false)
    @Timestamp
	private LocalDate data_criacao;

	@JoinColumn(name = "funcao_id")
	private Funcao funcao;

}
