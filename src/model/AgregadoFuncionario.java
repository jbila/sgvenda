package com.meldev.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_agregado_funcionario")
				
public class AgregadoFuncionario extends AbstractEntity {
	private String nome_completo;
	private LocalDate data_nascimento;
	private String relacao;//mae,pai, esposa,filho,enteado
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;
	

	public AgregadoFuncionario() {
	}

	public String getNome_completo() {
		return nome_completo;
	}

	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}

	

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public String getRelacao() {
		return relacao;
	}

	public void setRelacao(String relacao) {
		this.relacao = relacao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
	
		return getNome_completo();
	}
}
