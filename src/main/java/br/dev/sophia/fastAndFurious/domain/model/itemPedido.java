// Classe que modela os itens que compõem o pedido, utilizando a classe produto. 
package br.dev.sophia.fastAndFurious.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class itemPedido {

    @Id
    @GeneratedValue
    private Long id;
    
    private int quant;

    private String obs;
    private BigDecimal vUnit;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    
    
    public BigDecimal getVUnit() {
        return vUnit;
    }

    public void setvUnit(BigDecimal vUnit) {
        this.vUnit = vUnit;
    }

    public int getQuant() {
        return quant;
    }
    
    public void setQuant(int quant) {
        this.quant = quant;
    }
        public itemPedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final itemPedido other = (itemPedido) obj;
        return Objects.equals(this.id, other.id);
    }

}
