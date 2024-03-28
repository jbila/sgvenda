package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.TipoDespesa;
import com.meldev.utils.JpaUtil;

public class TipoDespesaDao implements GenericDao<TipoDespesa> {

	private final EntityManager entityManager;

	public TipoDespesaDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public TipoDespesa save(TipoDespesa TipoDespesa) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (TipoDespesa.getId() != null)
			entityManager.merge(TipoDespesa);
		else
			entityManager.persist(TipoDespesa);

		transaction.commit();
		return TipoDespesa;
	}

	@Override
	public List<TipoDespesa> saveAll(List<TipoDespesa> TipoDespesas) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		TipoDespesas.forEach(TipoDespesa -> entityManager.persist(TipoDespesa));
		transaction.commit();
		return TipoDespesas;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		TipoDespesa TipoDespesa = entityManager.find(TipoDespesa.class, id);
		if (TipoDespesa != null) {
			entityManager.remove(TipoDespesa);
		}
		transaction.commit();
	}

	@Override
	public List<TipoDespesa> findAll(Long empresaId) {
		TypedQuery<TipoDespesa> query = entityManager.createQuery("SELECT c FROM TipoDespesa c where c.empresa.id=:empresaId", TipoDespesa.class);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<TipoDespesa> findByName(String nome) {
		TypedQuery<TipoDespesa> query = entityManager.createQuery("SELECT c FROM TipoDespesa c WHERE c.nome = :nome",
				TipoDespesa.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	@Override
	public List<TipoDespesa> findAll(String nome,Long empresaId) {
		TypedQuery<TipoDespesa> query = entityManager.createQuery("SELECT c FROM TipoDespesa c WHERE c.nome LIKE :nome AND c.empresa.id=:empresaId",
				TipoDespesa.class);
		query.setParameter("nome", "%" + nome + "%");
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

}
