package com.paulo_brito.loja;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ClienteController {
    @Autowired
    ClienteRepository bd;

    @Autowired
    private EnvioEmail enviarEmail;

    @Transactional
    @PostMapping("/api/cliente")
    public void gravar(@RequestBody Cliente obj) {
        obj.setAtivo(0);
        bd.save(obj);
        System.out.println("Cliente cadastrado!");
        enviarEmail.enviarEmail(
            obj.getEmail(),"Confirmação de cadastro!",
            "Olá " + obj.getNome() 
            + ", seu cadastro foi concluído!"
        );
    }

    @Transactional
    @PutMapping("/api/cliente")
    public void alterar(@RequestBody Cliente obj) {
        if (bd.existsById(obj.getCodigo())) {
            bd.save(obj);
            System.out.println("Cliente alterado!");
        } else {
            System.out.println("Cliente não encontrado para alteração!");
        }
    }

    @GetMapping("/api/cliente/{codigo}")
    public Cliente carregar(@PathVariable("codigo") int id) {
        if (bd.existsById(id)) {
            System.out.println("Cliente encontrado!");
            return bd.findById(id).get();
        } else {
            return new Cliente();
        }
    }

    @Transactional
    @DeleteMapping("/api/cliente/{codigo}")
    public void apagar(@PathVariable("codigo") int id) {
        bd.deleteById(id);
        System.out.println("cliente removido com sucesso!");
    }

    @GetMapping("/api/clientes")
    public List<Cliente> listar() {
        return bd.findAll();
    }

    @PostMapping("/api/cliente/login")
    public Optional<Cliente> fazerLogin(@RequestBody Cliente obj) {
        Optional<Cliente> retorno = bd.fazerLogin(obj.getEmail(), obj.getSenha());
        if (retorno.isPresent()) {
            System.out.println("Login efetuado com sucesso!");
            return retorno;
        } else {
            System.out.println("Dados inválidos, tente novamente.");
            return Optional.empty();
        }
    }

    @GetMapping("/api/cliente/inativos")
    public List<Cliente> listarInativos() {
        return bd.listaInativos();
    }

    @Transactional
    @PatchMapping("/api/cliente/ativar/{email}")
    public Cliente ativarCliente(@PathVariable("email") String email) {
        Cliente cliente = bd.findByEmail(email);
        if (cliente != null) {
            cliente.setAtivo(1);
            bd.save(cliente);
            System.out.println("Email confirmado!");
            return cliente;
        } else {
            System.out.println("Cliente não localizado na base de dados!");
            return null;
        }
    }

    @GetMapping("/api/cliente/redefinirSenha")
    public String redefinirSenha(@RequestParam("email") String email) {
        Cliente cliente = bd.findByEmail(email);
        if (cliente != null) {
            String resetToken = "token_de_exemplo_123";
            String resetLink = "http://localhost:8080/api/cliente/redefinir-senha-confirmar?token=" + resetToken;

            enviarEmail.enviarEmail(
                cliente.getEmail(),
                "Redefinição de Senha",
                "Olá " + cliente.getNome() + ", clique no link para redefinir sua senha: " + resetLink
            );
            return "Link de redefinição enviado com sucesso!";
        } else {
            return "Email não localizado na base de dados!";
        }
    }
}