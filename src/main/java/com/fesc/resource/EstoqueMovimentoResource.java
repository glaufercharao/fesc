package com.fesc.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fesc.ServiceEstoqueMovimento;
import com.fesc.event.ResourceEvent;
import com.fesc.model.EstoqueMovimento;
import com.fesc.repository.EstoqueMovimentoRepository;

@RestController
@RequestMapping("/estoque")
public class EstoqueMovimentoResource {
	
	@Autowired
	private EstoqueMovimentoRepository estoqueMovimentoRepository;
	
	@Autowired
	private ServiceEstoqueMovimento serviceEstoque;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<EstoqueMovimento> listarEstoque(){
		return estoqueMovimentoRepository.findAll();
	}
	
	@PostMapping("/saida")
	public ResponseEntity<EstoqueMovimento> movimentoSaida(@Valid @RequestBody EstoqueMovimento em, HttpServletResponse response){
		
		EstoqueMovimento emRetorno = serviceEstoque.registrarSaida(em);
		publisher.publishEvent(new ResourceEvent(this, response, emRetorno.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(emRetorno);
		
	}
	
	@PostMapping("/entrada")
	public ResponseEntity<EstoqueMovimento> movimentoEntrada(@Valid @RequestBody EstoqueMovimento em, HttpServletResponse response){
		
		EstoqueMovimento emRetorno = serviceEstoque.registrarEntrada(em);
		publisher.publishEvent(new ResourceEvent(this, response, emRetorno.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(emRetorno);
		
	}

}
