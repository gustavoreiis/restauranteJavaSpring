package com.project.restaurante.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome não pode ser nulo")
    @Column(nullable = false)
    private String nome;

    @NotNull(message =  "CPF não pode ser nulo")
    @Column(nullable = false, unique = true)
    @Size(min = 8, max = 11)
    private String cpf;

    @Email(message = "Formato de email inválido")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Telefone não pode ser nulo")
    @Column(nullable = false)
    @Size(min = 8, max = 11)
    private String telefone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
