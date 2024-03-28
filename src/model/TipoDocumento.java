package com.meldev.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_tipo_documento")
public class TipoDocumento extends AbstractEntity {
	@Column(name = "codigo", unique = true)
	private String codigo;
	@Column(name = "data_emissao")
	private LocalDate dataEmissao;
	@Column(name = "data_validade")
	private LocalDate dataValidade;
	@Column(name = "desconto_comercial", columnDefinition = "double default 0.0")
	private double descontoComercial;
	private double total;
	@Column(name = "sub_total", columnDefinition = "double default 0.0")
	private double subTotal;

	@ManyToOne
	@JoinColumn(name = "id_documento")
	private Documento documento;
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name = "id_iva")
	private Iva iva;
	@ManyToOne
	@JoinColumn(name = "id_servico")
	private Servico servico;
	
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	public TipoDocumento() {
	}

	public TipoDocumento(String codigo, LocalDate dataEmissao, LocalDate dataValidade, double descontoComercial,
			double total, double subTotal, Documento documento, Cliente cliente, Iva iva, Servico servico) {
		super();
		this.codigo = codigo;
		this.dataEmissao = dataEmissao;
		this.dataValidade = dataValidade;
		this.descontoComercial = descontoComercial;
		this.total = total;
		this.subTotal = subTotal;
		this.documento = documento;
		this.cliente = cliente;
		this.iva = iva;
		this.servico = servico;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}

	public double getDescontoComercial() {
		return descontoComercial;
	}

	public void setDescontoComercial(double descontoComercial) {
		this.descontoComercial = descontoComercial;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Iva getIva() {
		return iva;
	}

	public void setIva(Iva iva) {
		this.iva = iva;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return getCodigo();
	}

}
