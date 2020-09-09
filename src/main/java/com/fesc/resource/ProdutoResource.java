package com.fesc.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.fesc.event.ResourceEvent;
import com.fesc.model.Produto;
import com.fesc.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Produto> listarProduto(){	
		return produtoRepository.findAll();
	}
	
	@PostMapping
	public  ResponseEntity<Produto> salvarProduto(@Valid @RequestBody Produto produto, HttpServletResponse response){
		
		Produto produtoSalvo = produtoRepository.save(produto);
		publisher.publishEvent(new ResourceEvent(this, response, produtoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}
	
	@PutMapping("/{codigo}")
	public  ResponseEntity<Produto> atualizarProduto(@PathVariable Long codigo, @Valid @RequestBody Produto produto, HttpServletResponse response, WebRequest request){
		
		Produto produtoSalvo = produtoRepository.findOne(codigo);
		
		if(produtoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(produto, produtoSalvo, "codigo");
		Produto produtoAtualizado = produtoRepository.save(produtoSalvo);
		publisher.publishEvent(new ResourceEvent(this, response, produtoAtualizado.getCodigo()));
		
		return ResponseEntity.ok(produtoSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerProduto(@PathVariable Long codigo) {
		produtoRepository.delete(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Produto> buscarProdutoId(@PathVariable Long codigo) {
		Produto produtoRecuperado = produtoRepository.findOne(codigo);
		return produtoRecuperado != null ? ResponseEntity.ok(produtoRecuperado) : ResponseEntity.notFound().build();
	}

}
