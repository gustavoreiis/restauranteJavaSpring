package com.project.restaurante.service;

import com.project.restaurante.model.Funcionario;
import com.project.restaurante.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService implements PessoaService<Funcionario> {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Override
    public ResponseEntity<?> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioRepository.findAll());
    }

    @Override
    public ResponseEntity<?> criar(Funcionario funcionario) {
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioRepository.save(funcionario));
    }

    @Override
    public ResponseEntity<?> atualizar(Funcionario funcionario, Long id) {
        for (Funcionario f: funcionarioRepository.findAll()) {
            if (f.getId() == id) {
                funcionario.setId(id);
                funcionarioRepository.save(funcionario);
                return ResponseEntity.status(HttpStatus.OK).body(funcionario);
            }
        }
        String message = "Id não encontrado.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

    }

    @Override
    public ResponseEntity<?> deletar(Long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            String message = "Item excluido com sucesso.";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else {
            String message = "Id não encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (funcionarioRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(funcionarioRepository.findById(id));
        } else {
            String message = "Id não encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @Override
    public ResponseEntity<?> quantidadePessoas() {
        int qtd = (int) funcionarioRepository.count();
        return ResponseEntity.status(HttpStatus.OK).body(qtd);
    }
}
