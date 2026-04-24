package br.dev.sophia.fastAndFurious.domain.service;

import br.dev.sophia.fastAndFurious.domain.model.Pedido;
import br.dev.sophia.fastAndFurious.domain.model.StatusPedido;
import br.dev.sophia.fastAndFurious.domain.repository.PedidoRepository;
import br.dev.sophia.fastAndFurious.exception.DomainException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }
    
    public Optional<Pedido> listarPorId(Long PedidoID) {
        return pedidoRepository.findById(PedidoID);
    }
    public Pedido atualizar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
    public List<Pedido> listarPorStatus(StatusPedido status) {
        return pedidoRepository.findByStatus(status);
    }
    public Optional<Pedido> atualizaStatus(Long pedidoID, StatusPedido status) {
        Optional<Pedido> optPedido = pedidoRepository.findById(pedidoID);
        
        if (optPedido.isPresent()) {
            
            Pedido pedido = optPedido.get();
            if (pedido.getStatus()==StatusPedido.ABERTO && status != StatusPedido.ABERTO) {
                pedido.setStatus(status);
                pedido.setDataEntregue(LocalDateTime.now());
                pedidoRepository.save(pedido);
                return Optional.of(pedido);
            }
            
            else {
                return Optional.empty();
            }
            
        } else {
            throw new DomainException("Não há um pedido com id" + pedidoID);
        }
    }
}


