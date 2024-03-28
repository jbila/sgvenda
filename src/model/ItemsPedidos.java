package com.meldev.model;

import model.Producto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_items_pedido")
public class ItemsPedidos extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;
	private int quantidade;
	@Column(name = "preco_unitario", columnDefinition = "double default 0.0")
	private double precoUnitario;
	@Column(name = "sub_total", columnDefinition = "double default 0.0")
	private double subTotal;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	public ItemsPedidos() {
	}

	public ItemsPedidos(Producto producto, Pedido pedido, int quantidade, double precoUnitario, double subTotal,
			String descricao) {
		super();
		this.producto = producto;
		this.pedido = pedido;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.subTotal = subTotal;

	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
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
	

}