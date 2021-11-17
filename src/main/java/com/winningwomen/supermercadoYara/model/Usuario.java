package com.winningwomen.supermercadoYara.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.winningwomen.supermercadoYara.dto.request.UsuarioRequest;

import jdk.jfr.Timestamp;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime data_criacao;
    
	@ManyToOne
	@JoinColumn(name = "funcao_id")
	private Funcao funcao;
}
