package br.dev.sophia.fastAndFurious.domain.DTO;

import br.dev.sophia.fastAndFurious.domain.model.StatusPedido;

    //DTO da atualização do status
public record AtualizaStatusPedidoDTO(
        StatusPedido status) {

}
