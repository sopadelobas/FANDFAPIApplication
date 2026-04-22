package br.dev.sophia.fastAndFurious.domain.controller;

import br.dev.sophia.fastAndFurious.domain.DTO.AtualizaStatusPedidoDTO;
import br.dev.sophia.fastAndFurious.domain.model.Pedido;
import br.dev.sophia.fastAndFurious.domain.model.StatusPedido;
import br.dev.sophia.fastAndFurious.domain.repository.PedidoRepository;
import br.dev.sophia.fastAndFurious.domain.service.PedidoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    @GetMapping
    public List<Pedido> listarTodos() {
        List<Pedido> listaPedidos = pedidoService.listar();
        return listaPedidos;
    }
    
    @GetMapping
    public ResponseEntity<Pedido> listarPorId(@PathVariable Long PedidoID) {
        Optional<Pedido> pedido = pedidoService.listarPorId(PedidoID);
        
        if (pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    } 
    
    @PutMapping("/pedidoID/")
    public ResponseEntity<Long> atualizar (@PathVariable Long pedidoID, @RequestBody Pedido dadosNovos) {
        Optional<Pedido> pedidoVelho = pedidoRepository.findById(pedidoID);
        
        if (pedidoVelho.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Pedido pedido = pedidoVelho.get();
        pedido.setNomeCliente(dadosNovos.getNomeCliente());
        pedido.setCpfCliente(dadosNovos.getCpfCliente());
        pedido.setStatus(dadosNovos.getStatus());
    
        pedidoRepository.save(pedido);
        return ResponseEntity.ok(pedidoID);
    }
    
    @GetMapping("/pedido/status/{statuspedido}")
    public ResponseEntity<List<Pedido>> listarPorStatus(@PathVariable StatusPedido status){ 
       return ResponseEntity.ok(pedidoService.listarPorStatus(status));      
    }
    
    @PutMapping("/statuspedido/{pedidoID}")
    public ResponseEntity<Pedido> modificarStatus(@PathVariable Long pedidoID, @RequestBody AtualizaStatusPedidoDTO statusDTO) {
        Optional<Pedido> pedidoStatus = pedidoService.atualizaStatus(pedidoID, statusDTO.status());
        
        if (pedidoStatus.isPresent()) {
            return ResponseEntity.ok(pedidoStatus.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
