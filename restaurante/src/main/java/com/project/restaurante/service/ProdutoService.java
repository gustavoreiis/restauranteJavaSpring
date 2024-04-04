package com.project.restaurante.service;

import com.project.restaurante.model.Produto;
import com.project.restaurante.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public ResponseEntity<?> atualizar(Produto produto, Long id) {
        for (Produto p : produtoRepository.findAll()) {
            if (p.getId() == id) {
                produto.setId(id);
                produtoRepository.save(produto);
                return ResponseEntity.ok(produto);
            }
        }
        String message = "Id não encontrado.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    public ResponseEntity<?> deletar(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            String message = "Item excluido com sucesso.";
            return ResponseEntity.ok().body(message);
        } else {
            String message = "Id não encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    public ResponseEntity<?> produtoById(Long id) {
        for (Produto p : produtoRepository.findAll()) {
            if (p.getId() == id) {
                return ResponseEntity.ok(p);
            }
        }
        String message = "Id não encontrado.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

}
