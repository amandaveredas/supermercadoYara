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

	private String nome;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private String descricao;
	private String imagem;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
}
