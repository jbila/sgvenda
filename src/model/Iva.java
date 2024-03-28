package com.meldev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_factura")
public class Iva extends AbstractEntity {
	@Column(name="nome",unique = true,nullable = false)
	private String nome;
	private double percentagem;
	private String descricao;

	public Iva() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public double getPercentagem() {
		return percentagem;
	}

	public void setPercentagem(double percentagem) {
		this.percentagem = percentagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return ""+getPercentagem();
	}

}
