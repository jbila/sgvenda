package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.Categoria;
import com.meldev.utils.JpaUtil;

public class CategoriaDao implements GenericDao<Categoria> {

	private final EntityManager entityManager;

	public CategoriaDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Categoria save(Categoria categoria) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (categoria.getId() != null) {
			entityManager.merge(categoria);

		} else {
			entityManager.persist(categoria);
		}
		transaction.commit();
		return categoria;
	}
	

	@Override
	public List<Categoria> saveAll(List<Categoria> categorias) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		categorias.forEach(categoria -> entityManager.persist(categoria));
		transaction.commit();
		return categorias;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Categoria categoria = entityManager.find(Categoria.class, id);
		if (categoria != null) {
			entityManager.remove(categoria);
		}
		transaction.commit();
	}

	@Override
	public List<Categoria> findAll(Long empresaId) {
	    TypedQuery<Categoria> query = entityManager.createQuery("SELECT c FROM Categoria c WHERE c.empresa.id = :empresaId", Categoria.class);
	    query.setParameter("empresaId", empresaId);
	    return query.getResultList();
	}


	public List<Categoria> findByName(String nome) {
		TypedQuery<Categoria> query = entityManager.createQuery("SELECT c FROM Categoria c WHERE c.nome = :nome",
				Categoria.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	@Override
	public List<Categoria> findAll(String nome,Long empresaId) {
		TypedQuery<Categoria> query = entityManager.createQuery(
				"SELECT c FROM Categoria c WHERE c.nome LIKE :nome OR c.descricao LIKE :nome AND c.empresa.id = :empresaId", Categoria.class);
		query.setParameter("nome", "%" + nome + "%");
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}
        
        public Long count() {
    TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(c) FROM Categoria c", Long.class);
    return query.getSingleResult();
}

}
