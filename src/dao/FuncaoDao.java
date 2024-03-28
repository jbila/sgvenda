package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.Funcao;
import com.meldev.utils.JpaUtil;

public class FuncaoDao implements GenericDao<Funcao> {

	private final EntityManager entityManager;

	public FuncaoDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Funcao save(Funcao Funcao) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if(Funcao.getId()!=null)
			entityManager.merge(Funcao);
		else
			entityManager.persist(Funcao);
		transaction.commit();
		return Funcao;
	}

	@Override
	public List<Funcao> saveAll(List<Funcao> Funcaos) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Funcaos.forEach(Funcao -> entityManager.persist(Funcao));
		transaction.commit();
		return Funcaos;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Funcao Funcao = entityManager.find(Funcao.class, id);
		if (Funcao != null) {
			entityManager.remove(Funcao);
		}
		transaction.commit();
	}

	@Override
	public List<Funcao> findAll(Long empresaId) {
		TypedQuery<Funcao> query = entityManager.createQuery("SELECT c FROM Funcao c where c.empresa.id=:empresaId", Funcao.class);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<Funcao> findByName(String nome) {
		TypedQuery<Funcao> query = entityManager.createQuery("SELECT c FROM Funcao c WHERE c.nome = :nome",
				Funcao.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	@Override
	public List<Funcao> findAll(String nome,Long empresaId) {
		TypedQuery<Funcao> query = entityManager.createQuery("SELECT c FROM Funcao c WHERE c.nome LIKE :nome AND c.empresa.id=:empresaId",
				Funcao.class);
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}



}
