package com.project.restaurante.controller;
import com.project.restaurante.model.Funcionario;
import com.project.restaurante.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<?> listarFuncionarios() {
        return funcionarioService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return funcionarioService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> criarFuncionario(@Valid @RequestBody Funcionario funcionario) {
        return funcionarioService.criar(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarFuncionario(@Valid @RequestBody Funcionario funcionario, @PathVariable Long id) {
        return funcionarioService.atualizar(funcionario, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarFuncionario(@PathVariable Long id) {
        return funcionarioService.deletar(id);
    }

    @GetMapping("/quantidade")
    public ResponseEntity<?> quantidadeFuncionarios() {
        return funcionarioService.quantidadePessoas();
    }
}
