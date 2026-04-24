
package br.dev.sophia.fastAndFurious.domain.service;
import br.dev.sophia.fastAndFurious.domain.model.Categoria;
import br.dev.sophia.fastAndFurious.domain.model.Produto;
import br.dev.sophia.fastAndFurious.domain.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


 @Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    public Produto criar (Produto produto) {
        
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new RuntimeException("Nome do produto é obrigatório!"); 
            
        }
        return produtoRepository.save(produto);
    }
    
    public void excluir (Long produtoID) {
        produtoRepository.deleteById(produtoID);
    }
    
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }
    
    public Optional<Produto> listarPorId(Long ProdutoID) {
        return produtoRepository.findById(ProdutoID);
    }
    
    public List<Produto> listarPorCategoria(String categoria) {
        return produtoRepository.findByCategoria(categoria);
    }
}
