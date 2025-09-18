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



@RestController 
@CrossOrigin(origins="*") 

public class ProductController {
    @Autowired
    private ProductRepository bd;

   @PostMapping("/api/produto")
   public void create(@RequestBody Product obj) {
    bd.save(obj);
    System.out.println("Produto criado!");
   }

   @PutMapping("/api/produto")
   public void alter(@RequestBody Product obj) {
    bd.save(obj);
    System.out.println("Produto alterado!");
   }

   @GetMapping("/api/produto/{codigo}")
   public Product search(@PathVariable("codigo") int codigo) {
    if(bd.existsById(codigo)) {
        return bd.findById(codigo).get();    
    }
    else {
        return new Product();
    }
   }

   @DeleteMapping("/api/produto/{codigo}") 
   @Transactional
   public void erase(@PathVariable("codigo") int codigo) {
    bd.deleteById(codigo);
    System.out.println("Produto removido!");
   }

   @GetMapping("/api/produtos") 
   public Iterable<Product> listAll() {
        return bd.findAll();
   }

   @GetMapping("/api/produtos/vitrine") 
   public Iterable<Product> listFeatured() {
    Sort ordenacao = Sort.by("destaque").ascending();
    return  bd.findAllFeatured(ordenacao);
   }

   @GetMapping("/api/produtos/busca?termo=") 
    public Iterable<Product> searchProducts(@RequestParam("termo") String termo) {
        return bd.findBySearchTerm(termo);
    }
}