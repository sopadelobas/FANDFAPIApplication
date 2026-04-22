
package br.dev.sophia.fastAndFurious.domain.controller;

import br.dev.sophia.fastAndFurious.domain.repository.ProdutoRepository;
import br.dev.sophia.fastAndFurious.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private ProdutoService produtoService;
    
    @GetMapping
    public ResponseEntity<Produto> listar() {
        
    }
}
