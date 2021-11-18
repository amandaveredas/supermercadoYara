package com.winningwomen.supermercadoYara.model;

import com.winningwomen.supermercadoYara.dto.request.UsuarioRequest;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name="usuario")
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Usuario {
	
	public Usuario(UsuarioRequest usuarioRequest, Funcao funcao) {
		this.setUser_name(usuarioRequest.getUser_name());
		this.setNome(usuarioRequest.getNome());
		this.setSobrenome(usuarioRequest.getSobrenome());
		this.setEmail(usuarioRequest.getEmail());
		this.setSenha(usuarioRequest.getSenha());
		this.setData_criacao(LocalDateTime.now());
		this.setFuncao(funcao);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Campo username não pode ser nulo.")
	@NotEmpty(message = "Campo username não pode ser vazio.")
    @Column(nullable = false, length = 15)
    private String user_name;

    @Column(nullable = false, length = 50)
	@NotNull(message = "Campo nome não pode ser nulo.")
	@NotEmpty(message = "Campo nome não pode ser vazio.")
	private String nome;
	@NotNull(message = "Campo sobrenome não pode ser nulo.")
	@NotEmpty(message = "Campo sobrenome não pode ser vazio.")
    @Column(nullable = false, length = 50)
	private String sobrenome;
	@NotNull(message = "Campo email não pode ser nulo.")
	@NotEmpty(message = "Campo email não pode ser vazio.")
    @Column(nullable = false, length = 150)
	private String email;
	@NotNull(message = "Campo senha não pode ser nulo.")
	@NotEmpty(message = "Campo senha não pode ser vazio.")
    @Column(nullable = false)
	private String senha;

    @Column(name = "data_criacao", updatable = false)
	@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime data_criacao;
    
	@ManyToOne
	@JoinColumn(name = "funcao_id")
	@NotNull(message = "Campo função não pode ser nulo.")
	private Funcao funcao;
}
