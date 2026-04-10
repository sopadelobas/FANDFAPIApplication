
package br.dev.sophia.fastAndFurious.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pedido {
 private long id;
 private String nomeCliente;
 private String cpfCliente;
 private BigDecimal valor;
 private LocalDateTime dataAbertura;
 private StatusPedido status;

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
 
  
     public Pedido() {
    }

    public Pedido(long id, String nomeCliente, String cpfCliente, BigDecimal valor) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.valor = valor;
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

      }
