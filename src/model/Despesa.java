package com.meldev.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_despesa")
public class Despesa extends AbstractEntity {

	private String descricao;
	@Column(name = "valor", nullable = true)
	private double valor;
	@Column(name = "data_pagamento")
	private LocalDate dataPagamento;
	private String status;// pago or nao
	private String categoria;// variavel or fixa
	@ManyToOne
	@JoinColumn(name = "id_tipo_despesa", nullable = true)
	private TipoDespesa tipoDespesa;
	@Column(name = "mes_referente")
	private String mesReferente;
	private int ano;
	@ManyToOne
	@JoinColumn(name = "id_conta")
	private Conta conta;
	// @Transient
	@ManyToOne
	@JoinColumn(name = "id_utilizador", nullable = true)
	private Utilizador utilizador;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	public Despesa() {
	}

	public Despesa(String descricao, double valor, LocalDate dataPagamento, String status, String categoria,
			TipoDespesa tipoDespesa) {
		super();
		this.descricao = descricao;
		this.valor = valor;
		this.dataPagamento = dataPagamento;
		this.status = status;
		this.categoria = categoria;
		this.tipoDespesa = tipoDespesa;
	}

	public String getMesReferente() {
		return mesReferente;
	}

	public void setMesReferente(String selectedMonth) {
		this.mesReferente = selectedMonth;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesa tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Despesa [descricao=" + descricao + ", valor=" + valor + ", dataPagamento=" + dataPagamento + ", status="
				+ status + ", categoria=" + categoria + ", tipoDespesa=" + tipoDespesa + ", utilizador=" + utilizador
				+ "]";
	}

}
