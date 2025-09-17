package com.paulo_brito.loja;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository // Interface que itera no BD.

public interface ProductRepository // Interface que herda os métodos CRUD
extends JpaRepository<Product, Integer>{
    @Modifying
    @Transactional
    
    @Query("delete from Product p where p.codigo = ?1")
    void deleteByCodigo(int codigo);

    @Query("SELECT p FROM Product p WHERE p.destaque > 0")
    Iterable<Product> findAllFeatured(Sort ordenacao); //Cria uma query usando o valor do campo "destaque" como parametro

    @Query(value = "SELECT p FROM Product p WHERE p.descricao LIKE %:termo% OR p.keywords LIKE %:termo%")
    Iterable<Product> findBySearchTerm(@Param("termo") String termo);
   

}


