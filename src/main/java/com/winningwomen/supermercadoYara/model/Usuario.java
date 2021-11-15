package com.winningwomen.supermercadoYara.model;

import java.time.LocalDate;

import javax.persistence.*;

import com.winningwomen.supermercadoYara.dto.request.UsuarioRequest;

import jdk.jfr.Timestamp;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="usuario")
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(nullable = false, length = 15)
    private String user_name;

    @Column(nullable = false, length = 50)
	private String nome;

    @Column(nullable = false, length = 50)
	private String sobrenome;

    @Column(nullable = false, length = 150)
	private String email;

    @Column(nullable = false)
	private String senha;

    @Column(name = "data_criacao", updatable = false)
    @Timestamp
	private LocalDate data_criacao;

	@JoinColumn(name = "funcao_id")
	@ManyToOne
	private Funcao funcao;

	public Usuario(UsuarioRequest usuarioRequest, Funcao funcao) {
		this.setUser_name(usuarioRequest.getUser_name());
		this.setNome(usuarioRequest.getNome());
		this.setSobrenome(usuarioRequest.getSobrenome());
		this.setEmail(usuarioRequest.getEmail());
		this.setSenha(usuarioRequest.getSenha());
		this.setData_criacao(usuarioRequest.getData_criacao());
		this.setFuncao(funcao);
	}

}
