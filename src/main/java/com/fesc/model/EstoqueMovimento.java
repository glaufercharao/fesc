package com.fesc.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fesc.enumerator.TipoMovimentacao;

@Entity
@Table(name="estoque_movimento")
public class EstoqueMovimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	@Column(name="tipo_movimentacao")
	private TipoMovimentacao movimentacao;
	
	@Column(name="valor_venda")
	private double valorVenda;
	
	@Column(name="data_venda")
	private Date dataVenda;
	
	@Column(name="quantidade_movimentada")
	private int quantidadeMovimentada;
	
	public EstoqueMovimento() {}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public TipoMovimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(TipoMovimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public int getQuantidadeMovimentada() {
		return quantidadeMovimentada;
	}

	public void setQuantidadeMovimentada(int quantidadeMovimentada) {
		this.quantidadeMovimentada = quantidadeMovimentada;
	}
	
	

}
