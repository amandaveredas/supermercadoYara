package com.winningwomen.supermercadoYara.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="produto")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private String descricao;
	private String imagem;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
}
