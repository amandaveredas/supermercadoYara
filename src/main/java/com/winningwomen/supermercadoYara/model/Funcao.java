package com.winningwomen.supermercadoYara.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.winningwomen.supermercadoYara.dto.request.FuncaoRequest;

import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="funcao")
@Getter
@Setter
@Builder
public class Funcao {
	
	public Funcao(FuncaoRequest funcaoRequest) {
		this.setNome(funcaoRequest.getNome());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Campo username não pode ser nulo.")
	@NotEmpty(message = "Campo user name não pode ser vazio.")
	private String nome;

	

}
