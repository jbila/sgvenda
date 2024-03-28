package com.meldev.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="tbl_parcela")
public class Parcela extends AbstractEntity {
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;// vai conter o numero de venda
	@ManyToOne
	@JoinColumn(name = "id_forma")
	private FormasDePagamento formasDepagamento;
	private double valorApagar;
	private String dataPrevista;
	private double juros;
	private String dataPagamento;
	private String estado;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;



	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public FormasDePagamento getFormasDepagamento() {
		return formasDepagamento;
	}

	public void setFormasDepagamento(FormasDePagamento formasDepagamento) {
		this.formasDepagamento = formasDepagamento;
	}

	public double getValorApagar() {
		return valorApagar;
	}

	public void setValorApagar(double valorApagar) {
		this.valorApagar = valorApagar;
	}

	public String getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public double getJuros() {
		return juros;
	}

	public void setJuros(double juros) {
		this.juros = juros;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	
	

	

}
