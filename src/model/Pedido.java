package com.meldev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_pedido")
public class Pedido extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name = "id_utilizador")
	private Utilizador utilizador;
	@ManyToOne
	@JoinColumn(name = "id_documento")
	private Documento documento;
	private double iva;
	@Column(name = "desconto_comercial")
	private double descontoComercial;
	private double total;
	@Column(name = "sub_total")
	private double subTotal;
	@Column(name = "status", columnDefinition = "varchar(20) default 'EMITIDA'")
	private String status;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	public Pedido() {

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
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
	

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Pedido [cliente=" + cliente + ", utilizador=" + utilizador + ", documento=" + documento + ", iva=" + iva
				+ ", descontoComercial=" + descontoComercial + ", total=" + total + ", subTotal=" + subTotal
				+ ", status=" + status + "]";
	}

}
