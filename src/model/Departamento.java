
package com.meldev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="tbl_departamento")
public class Departamento extends AbstractEntity{
	@Column(name="nome")
    private String nome;
    private String descricao;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;
 
	
	public Departamento( String nome, String descricao) {	
		this.nome = nome;
		this.descricao = descricao;
		
	}

	public Departamento() {
		
	}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}
		
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
