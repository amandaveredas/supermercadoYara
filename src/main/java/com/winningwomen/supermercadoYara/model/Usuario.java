package com.winningwomen.supermercadoYara.model;

import java.time.LocalDate;

import javax.persistence.*;

import jdk.jfr.Timestamp;
import lombok.Data;
@Data
@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@ManyToOne(cascade = CascadeType.ALL)

	@JoinColumn(name = "funcao_id")
	private Funcao funcao;

    @PrePersist
    public void prePersist(){setData_criacao((LocalDate.now()));}
    public long getId(){return id;}
    public void setId(long Id){this.id = id;}
    public String getUser_name(){return user_name;}
    public void setUser_name(String user_name){this.user_name = user_name;}
    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}
    public String getSobrenome(){return sobrenome;}
    public void setSobrenome(String sobrenome){this.sobrenome = sobrenome;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
    public String getSenha(){return senha;}
    public void setSenha(String senha){this.senha = senha;}
    public LocalDate getData_criacao(){return data_criacao = data_criacao;}
    public void setData_criacao(LocalDate data_criacao){this.data_criacao = data_criacao;}
}
