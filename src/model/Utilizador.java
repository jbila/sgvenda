
package com.meldev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="tbl_utilizador")
public class Utilizador extends AbstractEntity{
	
	private String status;
	private String password;
	@Column(name="username",unique = true)
	private String username;
	@ManyToOne
	@JoinColumn(name="id_role")
	private Role role;
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;
	
	public Utilizador() {
	}

	
	
	public Utilizador(String status, String password, String username, Role role) {
		this.status = status;
		this.password = password;
		this.username = username;
		this.role = role;
	}



	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public Empresa getEmpresa() {
		return empresa;
	}



	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}



	@Override
	public String toString() {
		return getUsername();
	}
	
	

	

}
