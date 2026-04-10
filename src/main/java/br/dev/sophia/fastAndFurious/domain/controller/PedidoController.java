
package br.dev.sophia.fastAndFurious.domain.controller;

import br.dev.sophia.fastAndFurious.domain.model.Pedido;
import br.dev.sophia.fastAndFurious.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/fastfurious/pedido")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;
    
    @PostMapping("/")
    public Pedido criar(@RequestBody Pedido pedido) {
        return pedidoService.criar(pedido);
    }
    
    @DeleteMapping
}
