package com.meldev.model;

import model.Producto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_stock")
public class Stock extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;
	@ManyToOne
	@JoinColumn(name = "id_fornecedor")
	private Fornecedor fornecedor;
	private int qty;
	@Column(name = "preco_compra", columnDefinition = "double default 0.0")
	private double precoCompra;
	@Column(name = "preco_venda", columnDefinition = "double default 0.0")
	private double precoVenda;
	@Column(columnDefinition = "integer default 0")
	private int max;
	@Column(columnDefinition = "integer default 0")
	private int min;
	private String validade;
	@Column(columnDefinition = "double default 0.0")
	private double lucro;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	public Stock() {
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public double getLucro() {
		return lucro;
	}

	public void setLucro(double lucro) {
		this.lucro = lucro;
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}
	

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

    @Override
    public String toString() {
        return "Stock{" + "producto=" + producto + ", fornecedor=" + fornecedor + ", qty=" + qty + ", precoCompra=" + precoCompra + ", precoVenda=" + precoVenda + ", max=" + max + ", min=" + min + ", validade=" + validade + ", lucro=" + lucro + ", empresa=" + empresa + '}';
    }

	
}
