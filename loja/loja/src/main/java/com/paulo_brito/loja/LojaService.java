package com.paulo_brito.loja;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class LojaService {

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private EnvioEmail envioEmail;

    @Transactional
    @PostMapping
    public Pedido criarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoRepo.save(pedido);

        envioEmail.enviarEmail(
                pedido.getCliente().getEmail(),
                "Confirmação de Pedido",
                        "Olá " + pedido.getCliente().getNome() 
                        + ", seu pedido foi realizado com sucesso!"
        );
        return novoPedido;
    }

    @Transactional
    @PutMapping
    public Pedido alterarPedido(@RequestBody Pedido pedido) {
        return pedidoRepo.save(pedido);
    }

    @Transactional
    @DeleteMapping("/{codigo}")
    public void removerPedido(@PathVariable int codigo) {
        pedidoRepo.deleteById(codigo);
    }

    @GetMapping("/{codigo}")
    public Pedido buscarPorCodigo(@PathVariable int codigo) {
        return pedidoRepo.findById(codigo).orElse(null);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Pedido> listarPorCliente(@PathVariable int clienteId) {
        return pedidoRepo.findByClienteId(clienteId);
    }

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoRepo.findAll();
    }
}
