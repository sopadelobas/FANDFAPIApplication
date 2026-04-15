package br.dev.sophia.fastAndFurious.domain.service;

import br.dev.sophia.fastAndFurious.domain.model.Pedido;
import br.dev.sophia.fastAndFurious.domain.model.StatusPedido;
import br.dev.sophia.fastAndFurious.domain.repository.PedidoRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;


public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criar(Pedido pedido) {
        pedido.setStatus(StatusPedido.ABERTO);
        pedido.setDataAbertura(LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }
    
    public void excluir(Long pedidoID) {
       pedidoRepository.deleteById(pedidoID);
       }
    
    }

