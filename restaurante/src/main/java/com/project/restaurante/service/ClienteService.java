package com.project.restaurante.service;

import com.project.restaurante.model.Cliente;
import com.project.restaurante.model.Pedido;
import com.project.restaurante.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService implements PessoaService<Cliente> {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public ResponseEntity<?> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }

    @Override
    public ResponseEntity<?> criar(Cliente cliente) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(cliente));
    }

    @Override
    public ResponseEntity<?> atualizar(Cliente cliente, Long id) {
        for (Cliente c: clienteRepository.findAll()) {
            if (c.getId() == id) {
                cliente.setId(id);
                clienteRepository.save(cliente);
                return ResponseEntity.status(HttpStatus.OK).body(cliente);
            }
        }
        String message = "Id não encontrado.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

    }

    @Override
    public ResponseEntity<?> deletar(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            String message = "Item excluido com sucesso.";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else {
            String message = "Id não encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        if (clienteRepository.existsById(id)) {
            return ResponseEntity.ok(clienteRepository.findById(id));
        } else {
            String message = "Id não encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @Override
    public ResponseEntity<?> quantidadePessoas() {
        int qtd = (int) clienteRepository.count();
        return ResponseEntity.status(HttpStatus.OK).body(qtd);
    }

    public ResponseEntity<?> listarPedidos(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente.get().getHistoricoPedidos());
    }
}
