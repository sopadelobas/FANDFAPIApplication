
package br.dev.sophia.fastAndFurious.domain.repository;

import br.dev.sophia.fastAndFurious.domain.model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    List<Produto> findByCategoria_Nome(String nome);
}

