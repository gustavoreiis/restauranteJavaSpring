package com.project.restaurante.controller;

import com.project.restaurante.model.Cliente;
import com.project.restaurante.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> listarClientes() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> criarCliente(@Valid @RequestBody Cliente cliente) {
        return clienteService.criar(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {
        return clienteService.atualizar(cliente, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id) {
        return clienteService.deletar(id);
    }

    @GetMapping("/historico/{id}")
    public ResponseEntity<?> listarPedidos(@PathVariable Long id) {
        return clienteService.listarPedidos(id);
    }

    @GetMapping("/quantidade")
    public ResponseEntity<?> quantidadeClientes() {
        return clienteService.quantidadePessoas();
    }
}
