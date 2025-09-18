package com.paulo_brito.loja;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PedidoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Product produto;
    private int quantidade;
    private double precoUnitario;

    public int getCodigo() {
         return codigo;
    }
    public void setCodigo(int codigo) { 
        this.codigo = codigo;
    }
    public Pedido getPedido() { 
        return pedido;
    }
    public void setPedido(Pedido pedido) { 
        this.pedido = pedido;
    }
    public Product getProduto() {  
        return produto;
    }
    public void setProduto(Product produto) {  
        this.produto = produto;
    }
    public int getQuantidade() { 
        return quantidade;
    }
    public void setQuantidade(int quantidade) { 
        this.quantidade = quantidade;
    }
    public double getPrecoUnitario() { 
        return precoUnitario;
    }
    public void setPrecoUnitario(double precoUnitario) { 
        this.precoUnitario = precoUnitario;
    }
}
