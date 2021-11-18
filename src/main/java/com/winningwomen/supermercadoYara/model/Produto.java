package com.winningwomen.supermercadoYara.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@Table(name="produto")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 15)
	@NotNull(message = "Campo nome não pode ser nulo.")
	@NotEmpty(message = "Campo nome não pode ser vazio.")
	private String nome;
	
	@Column(nullable = false)
	@NotNull(message = "Campo quantidade não pode ser nulo.")
	private Integer quantidade;
	
	@Column(nullable = false)
	@NotNull(message = "Campo precoUnitario não pode ser nulo.")
	private BigDecimal precoUnitario;
	
	@Column(length = 25)
	private String descricao;
	
	private String imagem;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	@NotNull(message = "Campo categoria não pode ser nulo.")
	private Categoria categoria;
	
}
