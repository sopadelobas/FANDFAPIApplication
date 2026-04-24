package br.dev.sophia.fastAndFurious.domain.controller;

import br.dev.sophia.fastAndFurious.domain.model.Produto;
import br.dev.sophia.fastAndFurious.domain.repository.ProdutoRepository;
import br.dev.sophia.fastAndFurious.domain.service.ProdutoService;
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
@RequestMapping("/fastfurious/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/")
    public List<Produto> listarTodos() {
        List<Produto> listaProdutos = produtoService.listar();
        return listaProdutos;
    }

    @GetMapping("/{produtoID}/")
    public ResponseEntity<Produto> listarPorId(@PathVariable Long ProdutoID) {
        Optional<Produto> produto = produtoService.listarPorId(ProdutoID);

        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{produtoID}")
    public ResponseEntity<Long> atualizarInf(@PathVariable Long produtoID, @RequestBody Produto dadosAtua) {
        Optional<Produto> produtoDesatu = produtoRepository.findById(produtoID);

        if (produtoDesatu.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Produto produto = produtoDesatu.get();
        produto.setNome(dadosAtua.getNome());
        produto.setPrecoUnit(dadosAtua.getPrecoUnit());
        produto.setDescrição(dadosAtua.getDescrição());

        produtoRepository.save(produto);
        return ResponseEntity.ok(produtoID);
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return produtoService.criar(produto);
    }

    @DeleteMapping("/{produtoID}/")
    public ResponseEntity<Void> excluir(@PathVariable Long produtoID) {
        if (!produtoRepository.existsById(produtoID)) {
            return ResponseEntity.notFound().build();
        }

        produtoService.excluir(produtoID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/produto/cat/{categoria}")
    public List<Produto> listarPorCategoria(@PathVariable String categoria) {
        List<Produto> listaCat = produtoService.listarPorCategoria(categoria);
        return listaCat;
    }
}

