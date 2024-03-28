
package com.meldev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table(
		name = "tbl_cliente"	
				)
public class Cliente extends AbstractEntity {
	@Column(name = "codigo",length = 8,unique = true)
	private String codigo;
	private String nome;
	@Column(name = "nuit",length = 9)
	private String nuit;
	private String genero;
	@Column(name="email")
	private String email;
	@Column(name="telefone")
	private String telefone;
	private String endereco;
	@ManyToOne
	@JoinColumn(name = "id_distrito")
	private Distrito distrito;
	@ManyToOne
	@JoinColumn(name = "id_provincia")
	private Provincia provincia;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	public Cliente() {
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
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
