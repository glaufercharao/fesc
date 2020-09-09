package com.fesc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fesc.enumerator.TipoMovimentacao;

@Entity
@Table(name="estoque_movimento")
public class EstoqueMovimento implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_produto", referencedColumnName = "codigo" )
	private Produto produto;
	
	@Column(name="tipo_movimentacao")
	@NotNull
	private TipoMovimentacao movimentacao;
	
	@Column(name="valor_venda")
	@NotNull
	private double valorVenda;
	
	@Column(name="data_venda")
	@NotNull
	private Date dataVenda;
	
	@Column(name="quantidade_movimentada")
	@NotNull
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
