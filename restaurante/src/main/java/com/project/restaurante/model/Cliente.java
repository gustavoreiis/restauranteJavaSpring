package com.project.restaurante.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente extends Pessoa {
    private String endereco;
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> historicoPedidos;

    public Cliente() {
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Pedido> getHistoricoPedidos() {
        return historicoPedidos;
    }

    public void setHistoricoPedidos(List<Pedido> historicoPedidos) {
        this.historicoPedidos = historicoPedidos;
    }
}
