package com.meldev.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="tbl_compra")
public class Compra extends AbstractEntity{
	@ManyToOne
	@JoinColumn(name = "id_utilizador")
	private Utilizador utilizador;
	private double valorCompra;
	private double valorPago;
	@ManyToOne
	@JoinColumn(name = "id_forma")
	private FormasDePagamento formasDepagamento;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;
	//idCompra,utilizador,valorCompra,valorPago,dataRegisto,formasPagamento
	public Compra() {
	}


	public Utilizador getProducto() {
		return utilizador;
	}

	public void setProducto(Utilizador producto) {
		this.utilizador = producto;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public double getValorVenda() {
		return valorPago;
	}

	public void setValorVenda(double valorVenda) {
		this.valorPago = valorVenda;
	}

	

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public FormasDePagamento getFormasDepagamento() {
		return formasDepagamento;
	}
	

	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public void setFormasDepagamento(FormasDePagamento formasDepagamento) {
		this.formasDepagamento = formasDepagamento;
	}


}
