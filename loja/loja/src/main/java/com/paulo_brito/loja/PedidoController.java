package com.paulo_brito.loja;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Pedido gravar(@RequestBody Pedido pedido) {
        var clienteId = pedido.getCliente().getCodigo();
        var cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado: " + clienteId));
        pedido.setCliente(cliente);

        if (pedido.getItens() != null) {
            for (PedidoItem item : pedido.getItens()) {
                int prodId = item.getProduto().getCodigo();
                var produto = productRepository.findById(prodId)
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + prodId));
                item.setProduto(produto);
                item.setPedido(pedido);
            }
        }
        pedido.calcularTotal();
        return pedidoRepository.save(pedido);
    }

    @PutMapping
    public Pedido alterar(@RequestBody Pedido pedido) {
        if (!pedidoRepository.existsById(pedido.getCodigo())) {
            throw new RuntimeException("Pedido não encontrado: " 
            + pedido.getCodigo());
        }
        
        var clienteId = pedido.getCliente().getCodigo();
        var cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado: " + clienteId));
        pedido.setCliente(cliente);

        if (pedido.getItens() != null) {
            for (PedidoItem item : pedido.getItens()) {
                int prodId = item.getProduto().getCodigo();
                var produto = productRepository.findById(prodId)
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + prodId));
                item.setProduto(produto);
                item.setPedido(pedido);
            }
        }

        pedido.calcularTotal();
        return pedidoRepository.save(pedido);
    }

    @DeleteMapping("/{codigo}")
    public void remover(@PathVariable int codigo) {
        pedidoRepository.deleteById(codigo);
    }

    @GetMapping("/{codigo}")
    public Pedido carregar(@PathVariable int codigo) {
        return pedidoRepository.findById(codigo).orElse(null);
    }

    @GetMapping("/cliente/{idCliente}")
    public List<Pedido> listarPorCliente(@PathVariable int idCliente) {
        return pedidoRepository.findByClienteId(idCliente);
    }

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }
}
