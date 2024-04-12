package com.meldev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_role")
public class Role extends AbstractEntity {
	@Column(name="nome",unique = true)
	private String nome;
	public Role() {
	}
        
        public Role(String nome) {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	@Override
	public String toString() {
		return getNome();
	}
	

	
}
