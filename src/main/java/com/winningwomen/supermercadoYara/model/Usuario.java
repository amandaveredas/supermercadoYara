package com.winningwomen.supermercadoYara.model;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;
@Data
@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	private Date data_criacao;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "funcao_id")
	private Funcao funcao;

}
