package com.meldev.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.TipoReceita;
import com.meldev.utils.JpaUtil;

public class TipoReceitaDao implements GenericDao<TipoReceita> {

	private final EntityManager entityManager;

	public TipoReceitaDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public TipoReceita save(TipoReceita TipoReceita) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if(TipoReceita.getId()!=null)
		entityManager.merge(TipoReceita);
		else
			entityManager.persist(TipoReceita);
		transaction.commit();
		return TipoReceita;
	}

	@Override
	public List<TipoReceita> saveAll(List<TipoReceita> TipoReceitas) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		TipoReceitas.forEach(TipoReceita -> entityManager.persist(TipoReceita));
		transaction.commit();
		return TipoReceitas;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		TipoReceita TipoReceita = entityManager.find(TipoReceita.class, id);
		if (TipoReceita != null) {
			entityManager.remove(TipoReceita);
		}
		transaction.commit();
	}

	@Override
	public List<TipoReceita> findAll(Long empresaId) {
		TypedQuery<TipoReceita> query = entityManager.createQuery("SELECT c FROM TipoReceita c where c.empresa.id=:empresaId", TipoReceita.class);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<TipoReceita> findByName(String nome) {
		TypedQuery<TipoReceita> query = entityManager.createQuery("SELECT c FROM TipoReceita c WHERE c.nome = :nome",
				TipoReceita.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	@Override
	public List<TipoReceita> findAll(String nome,Long empresaId) {
		TypedQuery<TipoReceita> query = entityManager.createQuery("SELECT c FROM TipoReceita c WHERE c.nome LIKE :nome AND c.empresa.id=:empresaId",
				TipoReceita.class);
		query.setParameter("nome","%"+ nome+"%");
		  query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public Optional<TipoReceita> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
