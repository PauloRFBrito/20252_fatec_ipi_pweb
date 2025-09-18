package com.paulo_brito.loja;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String marca;
    private String modelo;
    private String so;
    private String descricao;
    private String armazenamento;
    private String memoria;
    private String preco;
    private String promo;
    private String estoque;
    private int destaque;
    private String keywords;

    public int getCodigo() {
        return codigo;               
    }
      public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getSo() {
        return so;
    }
    public void setSo(String so) {
        this.so = so;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getArmazenamento() {
        return armazenamento;
    }
    public void setArmazenamento(String armazenamento) {
        this.armazenamento = armazenamento;
    }
    public String getMemoria() {
        return memoria;
    }
    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }
    public String getPreco() {
        return preco;
    }
    public void setPreco(String preco) {
        this.preco = preco;
    }
    public String getPromo() {
        return promo;
    }
    public void setPromo(String promo) {
        this.promo = promo;
    }
    public String getEstoque() {
        return estoque;
    }
    public void setEstoque(String estoque) {
        this.estoque = estoque;
    }
    public int getDestaque() {
        return destaque;
    }
    public void setDestaque(int destaque) {
        this.destaque = destaque;
    }
    public String getKeywords() {
        return keywords;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}