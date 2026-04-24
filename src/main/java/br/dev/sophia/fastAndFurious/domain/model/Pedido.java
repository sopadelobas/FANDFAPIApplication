package br.dev.sophia.fastAndFurious.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Pedido {

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<itemPedido> itens;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private String nomeCliente;
    private String cpfCliente;
    private BigDecimal valor;

    private LocalDateTime dataAbertura;
    private LocalDateTime dataPronto;
    private LocalDateTime dataEntregue;

    public LocalDateTime getDataPronto() {
        return dataPronto;
    }

    public void setDataPronto(LocalDateTime dataPronto) {
        this.dataPronto = dataPronto;
    }

    public LocalDateTime getDataEntregue() {
        return dataEntregue;
    }

    public void setDataEntregue(LocalDateTime dataEntregue) {
        this.dataEntregue = dataEntregue;
    }
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

    public void calcularTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (itemPedido item : itens) {
            BigDecimal subtotal = item.getVUnit().multiply(BigDecimal.valueOf(item.getQuant()));
            total = total.add(subtotal);
        }

        this.valor = total;

    }
}
