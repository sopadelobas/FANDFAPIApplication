
package br.dev.sophia.fastAndFurious.domain.model;

import java.math.BigDecimal;

public class Pedido {
 private long id;
 private String nomeCliente;
 private String cpfCliente;
 private BigDecimal valor;
 private Produto produto;

    public Pedido() {
    }

    public Pedido(long id, String nomeCliente, String cpfCliente, BigDecimal valor, Produto produto) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.valor = valor;
        this.produto = produto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    
}
