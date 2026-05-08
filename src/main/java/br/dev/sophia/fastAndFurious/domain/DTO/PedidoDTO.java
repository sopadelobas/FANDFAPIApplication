/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.dev.sophia.fastAndFurious.domain.DTO;

import java.util.List;

/**
 *
 * @author sesi3dib
 */
public class PedidoDTO {

    private String nomeCliente;
    private String cpfCliente;

    private List<itemPedidoDTO> itens;

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

    public List<itemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<itemPedidoDTO> itens) {
        this.itens = itens;
    }

    
    
}
