package com.paulo_brito.loja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository // Interface que itera no BD.

public interface ProductRepository // Interface que herda os métodos CRUD
extends JpaRepository<Product, Integer>{
    @Modifying
    @Transactional
    @Query("delete from Product p where p.codigo = ?1")
    void deleteByCodigo(int codigo);
}
