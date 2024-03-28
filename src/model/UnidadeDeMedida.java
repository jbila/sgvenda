package com.meldev.model;

import model.Producto;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_unidade_medida")
public class UnidadeDeMedida extends AbstractEntity {
	@Column(name = "nome")
	private String nome;
	private String descricao;
	@OneToMany(mappedBy = "unidadeDeMedida")
	private List<Producto> produtos;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	public UnidadeDeMedida() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
	}

	public List<Producto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Producto> produtos) {
		this.produtos = produtos;
	}
	

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return getNome();
	}

}
