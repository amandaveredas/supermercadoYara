package com.winningwomen.supermercadoYara.model;

import lombok.*;

import javax.persistence.*;
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
	private String nome;
	
	@Column(nullable = false)
	private Integer quantidade;
	
	@Column(nullable = false)
	private BigDecimal precoUnitario;
	
	@Column(nullable = false, length = 15)
	private String descricao;
	
	private String imagem;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
}
