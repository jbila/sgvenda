package com.meldev.model;

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
@Table(name = "tbl_funcionario")
public class Funcionario extends AbstractEntity {
	private String nome;
	private String apelido;
	private String genero;
	@Column(name = "email", unique = true)
	private String email;
	@Column(name = "telefone", unique = true)
	private String telefone;
	private String endereco;
	@Column(name = "imagem", unique = true, nullable = true)
	private String imagem;
	private double salario;
	@ManyToOne
	@JoinColumn(name = "id_distrito")
	private Distrito distrito;
	@ManyToOne
	@JoinColumn(name = "id_provincia", nullable = true)
	private Provincia provincia;
	@ManyToOne()
	@JoinColumn(name = "id_funcao")
	private Funcao funcao;
	@OneToMany(mappedBy = "funcionario",cascade = CascadeType.ALL)
	private List<AgregadoFuncionario> agregadoFuncionario;
	@OneToMany(mappedBy = "funcionario")
	private List<DocumentoFuncionario> documentoFuncionario;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	public Funcionario() {
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

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
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

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public List<AgregadoFuncionario> getAgregadoFuncionario() {
		return agregadoFuncionario;
	}

	public void setAgregadoFuncionario(List<AgregadoFuncionario> agregadoFuncionario) {
		this.agregadoFuncionario = agregadoFuncionario;
	}

	public List<DocumentoFuncionario> getDocumentoFuncionario() {
		return documentoFuncionario;
	}

	public void setDocumentoFuncionario(List<DocumentoFuncionario> documentoFuncionario) {
		this.documentoFuncionario = documentoFuncionario;
	}
	

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {

		return getNome() + " " + getApelido();
	}

}
