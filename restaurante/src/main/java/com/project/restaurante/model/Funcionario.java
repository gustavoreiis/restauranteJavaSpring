package com.project.restaurante.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Funcionario extends Pessoa {
    @NotNull
    private String cargo;
    @NotNull
    private float salario;
    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataContratacao;
    @NotNull
    private int numPedidosRealizados = 0;

    public Funcionario() {
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public int getNumPedidosRealizados() {
        return numPedidosRealizados;
    }

    public void setNumPedidosRealizados(int numPedidosRealizados) {
        this.numPedidosRealizados = numPedidosRealizados;
    }
}
