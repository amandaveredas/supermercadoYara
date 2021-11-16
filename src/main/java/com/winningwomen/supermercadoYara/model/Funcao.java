package com.winningwomen.supermercadoYara.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.winningwomen.supermercadoYara.dto.request.FuncaoRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="funcao")
public class Funcao {
	
	public Funcao(FuncaoRequest funcaoRequest) {
		this.setNome(funcaoRequest.getNome());
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String nome;
	

}
