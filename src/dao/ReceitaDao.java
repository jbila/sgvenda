package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.Receita;
import com.meldev.utils.JpaUtil;

public class ReceitaDao implements GenericDao<Receita> {

	private final EntityManager entityManager;

	public ReceitaDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Receita save(Receita Receita) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (Receita.getId() != null)
			entityManager.merge(Receita);
		else
			entityManager.persist(Receita);
		transaction.commit();
		return Receita;
	}

	@Override
	public List<Receita> saveAll(List<Receita> Receitas) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Receitas.forEach(Receita -> entityManager.persist(Receita));
		transaction.commit();
		return Receitas;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Receita Receita = entityManager.find(Receita.class, id);
		if (Receita != null) {
			entityManager.remove(Receita);
		}
		transaction.commit();
	}

	@Override
	public List<Receita> findAll(Long empresaId) {
		TypedQuery<Receita> query = entityManager.createQuery("SELECT c FROM Receita c where c.empresa.id=:empresaId",
				Receita.class);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<Receita> findByName(String nome, Long empresaId) {
		TypedQuery<Receita> query = entityManager
				.createQuery("SELECT c FROM Receita c WHERE c.nome = :nome AND c.empresa.id=:empresaId", Receita.class);
		query.setParameter("nome", nome);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	@Override
	public List<Receita> findAll(String nome, Long empresaId) {
		TypedQuery<Receita> query = entityManager.createQuery(
				"SELECT r FROM Receita r WHERE r.mesReferente LIKE :nome or r.ano like :nome AND r.empresa.id=:empresaId",
				Receita.class);
		query.setParameter("nome", "%" + nome + "%");
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<Receita> findByAno(Integer ano, Long empresaId) {
		TypedQuery<Receita> query = entityManager
				.createQuery("SELECT c FROM Receita c WHERE c.ano = :ano AND c.empresa.id=:empresaId", Receita.class);
		query.setParameter("ano", ano);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<Receita> findByAnoAndMes(Integer ano, String mes, Long empresaId) {
		TypedQuery<Receita> query = entityManager.createQuery(
				"SELECT c FROM Receita c WHERE c.ano = :ano AND c.mesReferente=:mes AND c.empresa.id=:empresaId",
				Receita.class);
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

}
