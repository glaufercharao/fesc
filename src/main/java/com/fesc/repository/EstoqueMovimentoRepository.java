package com.fesc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fesc.enumerator.TipoMovimentacao;
import com.fesc.model.EstoqueMovimento;

public interface EstoqueMovimentoRepository extends JpaRepository<EstoqueMovimento, Long> {
	  
	@Query("select em from EstoqueMovimento em where em.movimentacao = ?1")
	public List<EstoqueMovimento> consultarProdutoTipo(TipoMovimentacao tipo);
}
