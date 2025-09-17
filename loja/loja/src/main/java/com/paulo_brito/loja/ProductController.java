package com.paulo_brito.loja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController // Controlador REST
@CrossOrigin(origins="*") // Permite que outros domínios acessem a API.

public class ProductController {
    @Autowired
    private ProductRepository bd;

   @PostMapping("/api/produto") // Usado para criar novo recurso no sistema(inserir no banco)
   public void create(@RequestBody Product obj) { //RequestBody -> Extrai e converte body da requisição HTTP em obj Java da classe Product. 
    bd.save(obj);
    System.out.println("Produto criado!");
   }

   @PutMapping("/api/produto") // Usado para atuailzar recurso existente.
   public void alter(@RequestBody Product obj) {
    bd.save(obj);
    System.out.println("Produto alterado!");
   }

   @GetMapping("/api/produto/{codigo}") // Usado para buscar infos
   public Product search(@PathVariable("codigo") int codigo) { //PathVariable -> Extrai valor do parâmetro da URL para uma váriavel.
    if(bd.existsById(codigo)) {
        return bd.findById(codigo).get();    
    }
    else {
        return new Product();
    }
   }

   @DeleteMapping("/api/produto/{codigo}") //USado para deletar infos
   @Transactional
   public void erase(@PathVariable("codigo") int codigo) {
    bd.deleteById(codigo);
    System.out.println("Produto removido!");
   }

   @GetMapping("/api/produtos") //Listar todos os produtos
   public Iterable<Product> listAll() {
        return bd.findAll();
   }

   @GetMapping("/api/produtos/vitrine") //Listar os produtos por ordem de destaque 
   public Iterable<Product> listFeatured() {
    Sort ordenacao = Sort.by("destaque").ascending();
    return  bd.findAllFeatured(ordenacao);
   }

   @GetMapping("/api/produtos/key") //?termo=iphone
    public Iterable<Product> searchProducts(@RequestParam("termo") String termo) {
        return bd.findBySearchTerm(termo);
    }
}
