package com.fesc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fesc.model.EstoqueMovimento;
import com.fesc.model.Produto;
import com.fesc.repository.EstoqueMovimentoRepository;
import com.fesc.repository.ProdutoRepository;

@Service
public class ServiceEstoqueMovimento {
	
	@Autowired
	private EstoqueMovimentoRepository emRepository;
	
	@Autowired
	private ProdutoRepository pmRepository;
	
	public EstoqueMovimento registrarSaida(EstoqueMovimento em) {
		EstoqueMovimento emSalvo = null;
		int novaQuantidade = 0;
		Produto prodResult = pmRepository.findOne(em.getProduto().getCodigo());
		
		if(prodResult.getQuantidadeEstoque() > 0 
				&& prodResult.getQuantidadeEstoque() >= em.getQuantidadeMovimentada()) {
			
			novaQuantidade = prodResult.getQuantidadeEstoque() - em.getQuantidadeMovimentada();
			
			em.getProduto().setQuantidadeEstoque(novaQuantidade);
			prodResult.setQuantidadeEstoque(novaQuantidade);
			pmRepository.save(prodResult);
			emSalvo = emRepository.save(em);
		}
		return emSalvo;			
	}

	public EstoqueMovimento registrarEntrada(EstoqueMovimento em) {

		EstoqueMovimento emSalvo = null;
		int novaQuantidade = 0;
		Produto prodResult = pmRepository.findOne(em.getProduto().getCodigo());

		novaQuantidade = prodResult.getQuantidadeEstoque() + em.getQuantidadeMovimentada();
		
		em.getProduto().setQuantidadeEstoque(novaQuantidade);
		prodResult.setQuantidadeEstoque(novaQuantidade);
		pmRepository.save(prodResult);
		emSalvo = emRepository.save(em);
		
		return emSalvo;			
	}

}
