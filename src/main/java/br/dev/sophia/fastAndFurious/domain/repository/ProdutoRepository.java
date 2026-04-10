
package br.dev.sophia.fastAndFurious.domain.repository;

import br.dev.sophia.fastAndFurious.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{}

