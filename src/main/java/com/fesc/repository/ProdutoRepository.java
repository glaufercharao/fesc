package com.fesc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fesc.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
