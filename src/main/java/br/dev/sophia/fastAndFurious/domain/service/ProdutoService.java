
package br.dev.sophia.fastAndFurious.domain.service;
import br.dev.sophia.fastAndFurious.domain.model.Produto;
import br.dev.sophia.fastAndFurious.domain.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


 @Service
public class ProdutoService {
    
     //Import do repository
    @Autowired
    private ProdutoRepository produtoRepository;
    
     //Método para criar produtos || SERVICE
    public Produto criar (Produto produto) {
        
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new RuntimeException("Nome do produto é obrigatório!"); 
            
        }
        return produtoRepository.save(produto);
    }
    
    //Método para deletar produtos || SERVICE
    public void excluir (Long produtoID) {
        produtoRepository.deleteById(produtoID);
    }
    
    //Método para listar produtos || SERVICE
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }
    
    //Método para listar produtos por id || SERVICE
    public Optional<Produto> listarPorId(Long produtoID) {
        return produtoRepository.findById(produtoID);
    }
    
    //Método para listar produtos por categoria || SERVICE
    public List<Produto> listarPorCategoria(Long categoriaId) {
        return produtoRepository.findByCategoria_Id(categoriaId);
    }
}
