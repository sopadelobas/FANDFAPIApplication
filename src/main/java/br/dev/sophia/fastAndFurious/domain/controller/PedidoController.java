package br.dev.sophia.fastAndFurious.domain.controller;

import br.dev.sophia.fastAndFurious.domain.model.Pedido;
import br.dev.sophia.fastAndFurious.domain.repository.PedidoRepository;
import br.dev.sophia.fastAndFurious.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/fastfurious/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public Pedido criar(@RequestBody Pedido pedido) {
        return pedidoService.criar(pedido);
    }

    @DeleteMapping("/pedidoID/")
    public ResponseEntity<Void> excluir(@PathVariable Long pedidoID) {
        if (!pedidoRepository.existsById(pedidoID)) {
            return ResponseEntity.notFound().build();
        }
        
        pedidoService.excluir(pedidoID);
        return ResponseEntity.noContent().build();
    }
}
