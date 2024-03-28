package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.UnidadeDeMedida;
import com.meldev.utils.JpaUtil;

public class UnidadeDeMedidaDao implements GenericDao<UnidadeDeMedida> {

	private final EntityManager entityManager;

	public UnidadeDeMedidaDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public UnidadeDeMedida save(UnidadeDeMedida UnidadeDeMedida) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (UnidadeDeMedida.getId() != null)
			entityManager.merge(UnidadeDeMedida);
		else
			entityManager.persist(UnidadeDeMedida);
		transaction.commit();
		return UnidadeDeMedida;
	}

	@Override
	public List<UnidadeDeMedida> saveAll(List<UnidadeDeMedida> UnidadeDeMedidas) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		UnidadeDeMedidas.forEach(UnidadeDeMedida -> entityManager.persist(UnidadeDeMedida));
		transaction.commit();
		return UnidadeDeMedidas;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		UnidadeDeMedida UnidadeDeMedida = entityManager.find(UnidadeDeMedida.class, id);
		if (UnidadeDeMedida != null) {
			entityManager.remove(UnidadeDeMedida);
		}
		transaction.commit();
	}

	@Override
	public List<UnidadeDeMedida> findAll(Long empresaId) {
		TypedQuery<UnidadeDeMedida> query = entityManager.createQuery("SELECT c FROM UnidadeDeMedida c where c.empresa.id=:empresaId",
				UnidadeDeMedida.class);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<UnidadeDeMedida> findByName(String nome) {
		TypedQuery<UnidadeDeMedida> query = entityManager
				.createQuery("SELECT c FROM UnidadeDeMedida c WHERE c.nome = :nome", UnidadeDeMedida.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	@Override
	public List<UnidadeDeMedida> findAll(String nome,Long empresaId) {
		TypedQuery<UnidadeDeMedida> query = entityManager
				.createQuery("SELECT c FROM UnidadeDeMedida c WHERE c.nome = :nome AND c.empresa.id=:empresaId", UnidadeDeMedida.class);
		query.setParameter("nome", nome);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

}
