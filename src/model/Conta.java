
package com.meldev.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="tbl_conta")
public class Conta extends AbstractEntity{
	@Column(name="nome")
    private String nome;
    private String descricao;
    private double saldo;
    private String numero;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;
 
	public Conta(String nome, String descricao, double saldo, String numero) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.saldo = saldo;
		this.numero = numero;
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



	public double getSaldo() {
		return saldo;
	}



	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}



	public String getNumero() {
		return numero;
	}



	public void setNumero(String numero) {
		this.numero = numero;
	}



	public Conta() {
		
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
