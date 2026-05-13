
package br.dev.sophia.fastAndFurious.domain.DTO;

import java.util.List;

public class PedidoDTO {

    //Variáveis 
    private String nomeCliente;
    private String cpfCliente;
    
    //Lista
    private List<ItemPedidoDTO> itens;

    //Getters e setters
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

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }    
}
