package com.meldev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_tipo_despesa")
public class TipoDespesa extends AbstractEntity {
	@Column(name="nome",unique = true)
	private String nome;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	public TipoDespesa() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
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
