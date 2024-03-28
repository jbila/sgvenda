
package com.meldev.model;

import model.Producto;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_categoria")
public class Categoria extends AbstractEntity {
	@Column(name = "nome")
	private String nome;
	private String descricao;
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.REMOVE)
	private List<Producto> produtos;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	public Categoria(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;

	}

	public Categoria() {

	}

	public List<Producto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Producto> produtos) {
		this.produtos = produtos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase().trim();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.toLowerCase().trim();
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
