
package com.meldev.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_patrimonio")
public class Patrimonio extends AbstractEntity {
	private String codigo;
	private String descricao;
	private int qty;
	private double valor;
	@Column(name = "subt_total")
	private double subTotal = 0;
	private String status;// Activo ou inactivo
	@Column(name = "data_aquisicao")
	private LocalDate dataAquisicao;
	private String tipo;// activo,passivo
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	public Patrimonio() {

	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		if (subTotal == 0 || subTotal != 0) {
			subTotal = getQty() * getValor();
		}

		this.subTotal = subTotal;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo.toUpperCase();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(LocalDate dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Patrimonio [codigo=" + codigo + ", descricao=" + descricao + ", qty=" + qty + ", valor=" + valor
				+ ", status=" + status + ", dataAquisicao=" + dataAquisicao + "]";
	}

}
