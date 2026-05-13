package br.dev.sophia.fastAndFurious.domain.controller;

import br.dev.sophia.fastAndFurious.domain.model.Produto;
import br.dev.sophia.fastAndFurious.domain.repository.ProdutoRepository;
import br.dev.sophia.fastAndFurious.domain.service.ProdutoService;
import br.dev.sophia.fastAndFurious.exception.DomainException;
import jakarta.validation.Valid;
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
@RequestMapping("/fastfurious/produtos")
public class ProdutoController {

    //Import do Service e do Repository
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;
    
    //Método para listar todos os produtos
    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listar();
    }
    
     //Método para listar produtos por ID
    @GetMapping("/{produtoID}")
    public ResponseEntity<Produto> listarPorId(@PathVariable Long produtoID) {
        Optional<Produto> produto = produtoService.listarPorId(produtoID);

        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
     //Método para atualizar produtos
    @PutMapping("/{produtoID}")
    public ResponseEntity<Long> atualizarInf(@PathVariable Long produtoID, @RequestBody Produto dadosAtua) {
        Optional<Produto> produtoDesatu = produtoService.listarPorId(produtoID);

        if (produtoDesatu.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Produto produto = produtoDesatu.get();
        produto.setNome(dadosAtua.getNome());
        produto.setPrecoUnit(dadosAtua.getPrecoUnit());
        produto.setDescricao(dadosAtua.getDescricao());
        produto.setCategoria(dadosAtua.getCategoria());

        produtoRepository.save(produto);
        return ResponseEntity.ok(produtoID);
    }
    
     //Método para criar produtos
    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody @Valid Produto produto) {
        Produto novo = produtoService.criar(produto);
        return ResponseEntity.ok(novo);
    }

     //Método para deletar produtos
    @DeleteMapping("/{produtoID}")
    public ResponseEntity<Void> excluir(@PathVariable Long produtoID) {
        if (!produtoRepository.existsById(produtoID)) {
            return ResponseEntity.notFound().build();
        }

        produtoService.excluir(produtoID);
        return ResponseEntity.noContent().build();
    }

     //Método para listar todos os produtos por uma categoria
    @GetMapping("/cat/{categoriaId}")
    public List<Produto> listarPorCategoria(@PathVariable Long categoriaId) {
        List<Produto> listaCat = produtoService.listarPorCategoria(categoriaId);

        if (listaCat.isEmpty()) {
            throw new DomainException("Nenhum produto encontrado para essa categoria");
        }

        return listaCat;
    }
}
