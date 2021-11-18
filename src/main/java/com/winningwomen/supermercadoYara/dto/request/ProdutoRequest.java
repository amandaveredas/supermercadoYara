package com.winningwomen.supermercadoYara.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.math.BigDecimal;

@Data
public class ProdutoRequest {

	@NotEmpty(message = "Campo nome não pode ser vazio.")
	@NotNull(message = "Campo nome não pode ser nulo.")
	private String nome;
	@NotNull(message = "Campo quantidade não pode ser nulo.")
	private Integer quantidade;
	@NotNull(message = "Campo precoUnitario não pode ser nulo.")
	private BigDecimal precoUnitario;
	@NotNull(message = "Campo idCategoria não pode ser nulo.")
	private Long idCategoria;
	private String descricao;
	private MultipartFile imagem;

}
