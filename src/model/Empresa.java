package com.meldev.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_empresa")
public class Empresa extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	private String descricao;
	private String nome;
	private String nuit;
	private String endereco;
	private String email;
	private String telefone;
	private double iva;
	@OneToMany(mappedBy = "empresa")
	private List<Utilizador> users;
	
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNuit() {
		return nuit;
	}

	public void setNuit(String nuit) {
		this.nuit = nuit;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public List<Utilizador> getUsers() {
		return users;
	}

	public void setUsers(List<Utilizador> users) {
		this.users = users;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    @Override
    public String toString() {
        return "Empresa{" + "descricao=" + descricao + ", nome=" + nome + ", nuit=" + nuit + ", endereco=" + endereco + ", email=" + email + ", telefone=" + telefone + ", iva=" + iva + ", users=" + users + '}';
    }

	
        
	
	
	
	
	
	
	
	
	
	
	
}
