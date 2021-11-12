package com.winningwomen.supermercadoYara.model;

import javax.persistence.*;

import lombok.Data;
@Data
@Entity
@Table(name="categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 50)
	private String nome;

	public String getNome(){return nome;}
	public void setNome(String nome){this.nome = nome;}
}
