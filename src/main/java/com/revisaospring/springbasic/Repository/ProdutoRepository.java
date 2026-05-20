package com.revisaospring.springbasic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revisaospring.springbasic.Entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
