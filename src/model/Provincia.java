package com.meldev.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="tbl_provincia")
public class Provincia  extends AbstractEntity{
	private String nome;
	private String descricao;
	@OneToMany(mappedBy = "provincia")
    private List<Distrito> distritos;
	
	@OneToMany(mappedBy = "provincia")
	private List<Funcionario> funcionario;
	@OneToMany(mappedBy = "provincia")
	private List<Cliente> clientes;
	
	public Provincia() {
	}
	
	public List<Funcionario> getFuncionario() {
		return funcionario;
	}



	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}



	public List<Distrito> getDistritos() {
		return distritos;
	}



	public void setDistritos(List<Distrito> distritos) {
		this.distritos = distritos;
	}



	public List<Cliente> getClientes() {
		return clientes;
	}



	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
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
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return getNome();
	}

}
