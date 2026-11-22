package com.gestaoProdutos.gestaoProdutos.repository;

import com.gestaoProdutos.gestaoProdutos.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
