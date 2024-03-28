
package model;

import com.meldev.model.AbstractEntity;
import com.meldev.model.Categoria;
import com.meldev.model.Empresa;
import com.meldev.model.UnidadeDeMedida;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbl_producto")
public class Producto extends AbstractEntity {
	@Column(name = "codigo", unique = true, nullable = false)
	private String codigo;
	private String nome;
	private String descricao;
	private String estado;
	private double medida;
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	@ManyToOne
	@JoinColumn(name = "id_unidade_medida")
	private UnidadeDeMedida unidadeDeMedida;
	@Column(name = "picture_path")
	private String picturePath;
	@OneToMany(mappedBy = "producto")
	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	

	public Producto() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase().trim();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.toLowerCase().trim();
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	
	public String getestado() {
		return estado;
	}

	public void setestado(String estado) {
		this.estado = estado;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo.toUpperCase().trim();
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public UnidadeDeMedida getUnidadeDeMedida() {
		return unidadeDeMedida;
	}

	public void setUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
		this.unidadeDeMedida = unidadeDeMedida;
	}

	public double getMedida() {
		return medida;
	}

	public void setMedida(double medida) {
		this.medida = medida;
	}


	

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	
	@Override
	public String toString() {
		return getNome();
	}

}
