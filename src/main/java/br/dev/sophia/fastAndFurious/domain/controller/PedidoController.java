package br.dev.sophia.fastAndFurious.domain.controller;

import br.dev.sophia.fastAndFurious.domain.DTO.AtualizaStatusPedidoDTO;
import br.dev.sophia.fastAndFurious.domain.DTO.PedidoDTO;
import br.dev.sophia.fastAndFurious.domain.DTO.itemPedidoDTO;
import br.dev.sophia.fastAndFurious.domain.model.ItemPedido;
import br.dev.sophia.fastAndFurious.domain.model.Pedido;
import br.dev.sophia.fastAndFurious.domain.model.Produto;
import br.dev.sophia.fastAndFurious.domain.model.StatusPedido;
import br.dev.sophia.fastAndFurious.domain.repository.PedidoRepository;
import br.dev.sophia.fastAndFurious.domain.repository.ProdutoRepository;
import br.dev.sophia.fastAndFurious.domain.service.PedidoService;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fastfurious/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // Criação do método para criar pedidos 
    @PostMapping
    public Pedido criar(@RequestBody Pedido pedido) {
        return pedidoService.criar(pedido);
    }

    // Criação do método para excluir pedidos
    @DeleteMapping("{pedidoID}")
    public ResponseEntity<Void> excluir(@PathVariable Long pedidoID) {
        if (!pedidoRepository.existsById(pedidoID)) {
            return ResponseEntity.notFound().build();
        }

        pedidoService.excluir(pedidoID);
        return ResponseEntity.noContent().build();
    }

    // Criação do método para listar pedidos 
    @GetMapping
    public List<Pedido> listarTodos() {
        List<Pedido> listaPedidos = pedidoService.listar();
        return listaPedidos;
    }

    // Criação do método para listar por id  
    @GetMapping("/{pedidoID}")
    public ResponseEntity<Pedido> listarPorId(@PathVariable Long pedidoID) {
        Optional<Pedido> pedido = pedidoService.listarPorId(pedidoID);

        if (pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Criação do método para atualizar pedidos
    @PutMapping("/{pedidoID}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Long pedidoID,
            @RequestBody Pedido dadosNovos, PedidoDTO dto ) {

        Optional<Pedido> pedidoVelho = pedidoRepository.findById(pedidoID);

        if (pedidoVelho.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Pedido pedido = pedidoVelho.get();
        pedido.setNomeCliente(dadosNovos.getNomeCliente());
        pedido.setCpfCliente(dadosNovos.getCpfCliente());
        pedido.setStatus(dadosNovos.getStatus());

        if (pedido.getItens() == null) {
            pedido.setItens(new ArrayList<>());
        } else {
            pedido.getItens().clear();
        }
 
    if (dto.getItens() != null) {

        for (itemPedidoDTO itemDTO : dto.getItens()) {

            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            ItemPedido item = new ItemPedido();
            item.setQuant(itemDTO.getQuant());
            item.setObs(itemDTO.getObs());
            item.setVUnit(itemDTO.getVUnit());

            item.setProduto(produto);
            item.setPedido(pedido);

            pedido.getItens().add(item);
        }
    }

    pedido.calcularTotal();


            return ResponseEntity.ok(pedidoRepository.save(pedido));
        }

        // Criação do método para listar por status
        @GetMapping("/status/{status}")
        public ResponseEntity<List<Pedido>> listarPorStatus
        (@PathVariable
        StatusPedido status
        
            ) {
        return ResponseEntity.ok(pedidoService.listarPorStatus(status));
        }

        // Criação do método para mudar status dos pedidos 
        @PutMapping("/statuspedido/{pedidoID}")
        public ResponseEntity<Pedido> modificarStatus
        (@PathVariable
        Long pedidoID, @RequestBody AtualizaStatusPedidoDTO statusDTO
        
            ) {
        Optional<Pedido> pedidoStatus = pedidoService.atualizaStatus(pedidoID, statusDTO.status());

            if (pedidoStatus.isPresent()) {
                return ResponseEntity.ok(pedidoStatus.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }
