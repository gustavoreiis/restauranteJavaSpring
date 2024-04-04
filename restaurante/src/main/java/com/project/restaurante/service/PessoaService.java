package com.project.restaurante.service;

import com.project.restaurante.model.Pessoa;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PessoaService<T extends Pessoa> {
    ResponseEntity<?> listar();
    ResponseEntity<?> criar(T t);
    ResponseEntity<?> atualizar(T t, Long id);
    ResponseEntity<?> deletar(Long id);
    ResponseEntity<?> findById(Long id);

    ResponseEntity<?> quantidadePessoas();
}
