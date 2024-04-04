package com.project.restaurante.service;

import com.project.restaurante.model.Cliente;
import com.project.restaurante.model.Funcionario;
import com.project.restaurante.model.Pedido;
import com.project.restaurante.model.Produto;
import com.project.restaurante.repository.ClienteRepository;
import com.project.restaurante.repository.FuncionarioRepository;
import com.project.restaurante.repository.PedidoRepository;
import com.project.restaurante.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Autowired
    ClienteRepository clienteRepository;

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public boolean isPedidoValid(Pedido pedido) {
        Long produtoId = pedido.getProduto();
        Long atendenteId = pedido.getAtendente();
        Long clienteId = pedido.getCliente();

        if (produtoRepository.existsById(produtoId) &&
                funcionarioRepository.existsById(atendenteId) &&
                clienteRepository.existsById(clienteId)) {
            return true;
        }
        return false;
    }

    public ResponseEntity<?> criarPedido(Pedido pedido) {
        if (isPedidoValid(pedido)) {

            Long idCliente = pedido.getCliente();
            clienteRepository.findById(idCliente).get().getHistoricoPedidos().add(pedido);
            bonificacaoFuncionario(pedido);
            pedidoRepository.save(pedido);
            return ResponseEntity.status(HttpStatus.OK).body(pedido);
        } else {
            String message1 = "";
            String message2 = "";
            String message3 = "";
            if (!produtoRepository.existsById(pedido.getProduto())) {
                message1 = "Produto inexistente. ";
            }
            if (!funcionarioRepository.existsById(pedido.getAtendente())) {
                message2 = "Funcionário inexistente. ";
            }
            if (!clienteRepository.existsById(pedido.getCliente())) {
                message3 = "Cliente inexistente.";
            }
            String message = message1 + message2 + message3;
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    public void bonificacaoFuncionario(Pedido pedido) {
        Long idAtendente = pedido.getAtendente();
        Funcionario atendente = funcionarioRepository.findById(idAtendente).get();

        atendente.setNumPedidosRealizados(atendente.getNumPedidosRealizados() + 1);
        if (atendente.getNumPedidosRealizados() == 10) {
            atendente.setSalario((float) (atendente.getSalario() + atendente.getSalario() * 0.1));
            atendente.setNumPedidosRealizados(0);
        }
    }

    public ResponseEntity<?> atualizarPedido(Pedido pedido, Long id) {
        if (pedidoRepository.existsById(id)) {
            if (isPedidoValid(pedido)) {
                pedido.setId(id);
                pedidoRepository.save(pedido);
                return ResponseEntity.status(HttpStatus.OK).body(pedido);
            } else {
                String message = "Pedido inválido.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }
        } else {
            String message = "Id não encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    public ResponseEntity<?> deletarPedido(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            String message = "Item excluido com sucesso.";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else {
            String message = "Id não encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    public ResponseEntity<?> findById(Long id) {
        if (pedidoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(pedidoRepository.findById(id));
        } else {
            String message = "Id não encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    public ResponseEntity<?> exibirInformacoes(Long id) {
        if (pedidoRepository.existsById(id)) {
            Optional<Pedido> pedido = pedidoRepository.findById(id);
            Long idFuncionario = pedido.get().getAtendente();
            Long idCliente = pedido.get().getCliente();
            Long idProduto = pedido.get().getProduto();

            Funcionario funcionario = funcionarioRepository.findById(idFuncionario).get();
            Cliente cliente = clienteRepository.findById(idCliente).get();
            Produto produto = produtoRepository.findById(idProduto).get();

            Object[] responseArray = new Object[]{funcionario, cliente, produto};
            return ResponseEntity.status(HttpStatus.OK).body(responseArray);
        } else {
            String message = "Id não encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    public ResponseEntity<?> exibirInformacoesDetalhadas(Long id, String objeto) {
        ResponseEntity<?> response = exibirInformacoes(id);

        if (response.getStatusCode() == HttpStatus.OK) {
            Object[] responseArray = (Object[]) response.getBody();

            switch (objeto) {
                case "atendente":
                    Funcionario atendente = (Funcionario) responseArray[0];
                    return ResponseEntity.status(HttpStatus.OK).body(atendente);
                case "cliente":
                    Cliente cliente = (Cliente) responseArray[1];
                    return ResponseEntity.status(HttpStatus.OK).body(cliente);
                case "produto":
                    Produto produto = (Produto) responseArray[2];
                    return ResponseEntity.status(HttpStatus.OK).body(produto);
            }
        }
        return response;
    }
}
