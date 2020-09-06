package com.fesc.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fesc.model.Produto;
import com.fesc.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto> listarProduto(){	
		return produtoRepository.findAll();
	}
	
	@PostMapping
	public  ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto, HttpServletResponse response){
		Produto produtoSalvo = produtoRepository.save(produto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
					.buildAndExpand(produtoSalvo.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(produtoSalvo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Produto> buscarProdutoId(@PathVariable Long codigo) {
		Optional<Produto> produtoRecuperado = produtoRepository.findById(codigo);
		return !produtoRecuperado.isEmpty() ? ResponseEntity.ok(produtoRecuperado.get()) : ResponseEntity.notFound().build();
	}

}
