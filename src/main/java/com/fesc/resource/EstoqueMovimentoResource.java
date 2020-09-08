package com.fesc.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fesc.model.EstoqueMovimento;
import com.fesc.repository.EstoqueMovimentoRepository;

@RestController
@RequestMapping("/estoque")
public class EstoqueMovimentoResource {
	
	@Autowired
	private EstoqueMovimentoRepository estoqueMovimentoRepository;
	
	@GetMapping
	public List<EstoqueMovimento> listarEstoque(){
		return estoqueMovimentoRepository.findAll();
	}

}
