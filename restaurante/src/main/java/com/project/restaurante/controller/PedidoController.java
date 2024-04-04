package com.project.restaurante.controller;

import com.project.restaurante.model.Cliente;
import com.project.restaurante.model.Funcionario;
import com.project.restaurante.model.Pedido;
import com.project.restaurante.model.Produto;
import com.project.restaurante.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return pedidoService.findById(id);
    }

    @GetMapping("/detalhes/{id}")
    public ResponseEntity<?> exibirInformacoes(@PathVariable Long id) {
        return pedidoService.exibirInformacoes(id);
    }

    @GetMapping("/detalhes/{id}/{objeto}")
    public ResponseEntity<?> exibirInformacoesDetalhadas(@PathVariable Long id, @PathVariable String objeto) {
        return pedidoService.exibirInformacoesDetalhadas(id, objeto);
    }

    @PostMapping
    public ResponseEntity<?> criarPedido(@Valid @RequestBody Pedido pedido) {
        return pedidoService.criarPedido(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPedido(@Valid @RequestBody Pedido pedido, @PathVariable Long id) {
        return pedidoService.atualizarPedido(pedido, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPedido(@PathVariable Long id) {
        return pedidoService.deletarPedido(id);
    }
}
