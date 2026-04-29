package br.dev.sophia.fastAndFurious.domain.service;

import br.dev.sophia.fastAndFurious.domain.model.Pedido;
import br.dev.sophia.fastAndFurious.domain.model.StatusPedido;
import br.dev.sophia.fastAndFurious.domain.model.ItemPedido;
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

    // Criação do método para criar pedidos || SERVICE
    public Pedido criar(Pedido pedido) {
        pedido.setStatus(StatusPedido.ABERTO);
        pedido.setDataAbertura(LocalDateTime.now());

        if (pedido.getItens() != null) {
            for (ItemPedido item : pedido.getItens()) {
                item.setPedido(pedido);
            }
        }

        pedido.calcularTotal();
        return pedidoRepository.save(pedido);
    }

    // Criação do método para excluir pedidos || SERVICE
    public void excluir(Long pedidoID) {
        pedidoRepository.deleteById(pedidoID);
    }

    // Criação do método para listar pedidos || SERVICE
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    // Criação do método para listar por id || SERVICE
    public Optional<Pedido> listarPorId(Long PedidoID) {
        return pedidoRepository.findById(PedidoID);
    }

    // Criação do método para atualizar pedido || SERVICE
    public Pedido atualizar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    // Criação do método para listar por status || SERVICE
    public List<Pedido> listarPorStatus(StatusPedido status) {
        return pedidoRepository.findByStatus(status);
    }

    // Criação do método para atualizar status || SERVICE
    public Optional<Pedido> atualizaStatus(Long pedidoID, StatusPedido status) {
        Optional<Pedido> optPedido = pedidoRepository.findById(pedidoID);

        if (optPedido.isEmpty()) {
            throw new DomainException("Não há um pedido com id" + pedidoID);
        }

        Pedido pedido = optPedido.get();
        StatusPedido statusAtual = pedido.getStatus();

        boolean valido = false;

        if (statusAtual == StatusPedido.ABERTO && (status == StatusPedido.ENTREGUE || status == StatusPedido.CANCELADO)) {
            valido = true;
        }

        if (statusAtual == StatusPedido.ENTREGUE) {
            throw new RuntimeException("Pedido já finalizado!");
        }
        if (status == StatusPedido.ENTREGUE) {
            valido = true;
            pedido.setDataEntregue(LocalDateTime.now());
        }

        if (!valido) {
            throw new RuntimeException("Mudança de status inválida: " + statusAtual + "-->" + status);
        }

        pedido.setStatus(status);
        pedidoRepository.save(pedido);

        return Optional.of(pedido);
    }
}
