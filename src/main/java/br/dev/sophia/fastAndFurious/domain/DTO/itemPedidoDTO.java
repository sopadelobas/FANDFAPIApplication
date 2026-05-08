
package br.dev.sophia.fastAndFurious.domain.DTO;

import java.math.BigDecimal;

public class itemPedidoDTO {
    
    private int quant;
    private String obs;
    private BigDecimal vUnit;
    private Long produtoId;
    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public BigDecimal getVUnit() {
        return vUnit;
    }

    public void setVUnit(BigDecimal vUnit) {
        this.vUnit = vUnit;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }
    
   
    
}
