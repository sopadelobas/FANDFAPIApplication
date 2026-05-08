package br.dev.sophia.fastAndFurious.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "pedido")
public class Pedido {

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome_cliente")
    private String nomeCliente;
    
    @Column(name = "cpf_cliente")
    private String cpfCliente;
    
    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "data_abertura")
    private LocalDateTime dataAbertura;
    
    @Column(name = "data_pronto")
    private LocalDateTime dataPronto;
    
    @Column(name = "data_entregue")
    private LocalDateTime dataEntregue;
    
    @Column(name = "data_cancelado")
    private LocalDateTime dataCancelado;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido")
    private StatusPedido status;


    public LocalDateTime getDataCancelado() {
        return dataCancelado;
    }

    public void setDataCancelado(LocalDateTime dataCancelado) {
        this.dataCancelado = dataCancelado;
    }

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

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
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
        for (ItemPedido item : itens) {
            BigDecimal subtotal = item.getVUnit().multiply(BigDecimal.valueOf(item.getQuant()));
            total = total.add(subtotal);
        }

        this.valor = total;

    }
}
