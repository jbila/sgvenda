package com.meldev.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.meldev.model.Utilizador;
import com.meldev.utils.JpaUtil;

public class UtilizadorDao implements GenericDao<Utilizador> {

	private final EntityManager entityManager;

	public UtilizadorDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Utilizador save(Utilizador Utilizador) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (Utilizador.getId() != null)
			entityManager.merge(Utilizador);
		else
			entityManager.persist(Utilizador);

		transaction.commit();
		return Utilizador;
	}

	@Override
	public List<Utilizador> saveAll(List<Utilizador> Utilizadors) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Utilizadors.forEach(Utilizador -> entityManager.persist(Utilizador));
		transaction.commit();
		return Utilizadors;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Utilizador Utilizador = entityManager.find(Utilizador.class, id);
		if (Utilizador != null) {
			entityManager.remove(Utilizador);
		}
		transaction.commit();
	}

	@Override
	public List<Utilizador> findAll(Long empresaId) {
		TypedQuery<Utilizador> query = entityManager
				.createQuery("SELECT c FROM Utilizador c where c.empresa.id=:empresaId", Utilizador.class);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public Optional<Utilizador> findByName(String nome) {
		try {
			TypedQuery<Utilizador> query = entityManager
					.createQuery("SELECT u FROM Utilizador u WHERE u.username = :nome", Utilizador.class);
			query.setParameter("nome", nome);
			return Optional.ofNullable(query.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<Utilizador> findAll(String username,Long empresaId) {
		String jpql = "SELECT u FROM Utilizador u WHERE u.username LIKE CONCAT(:username, '%') AND u.empresa.id=:empresaId";
		TypedQuery<Utilizador> query = entityManager.createQuery(jpql, Utilizador.class);
		query.setParameter("username", username);
		 query.setParameter("empresaId", empresaId);
		List<Utilizador> utilizadores = query.getResultList();
		return utilizadores;
	}

	public Utilizador login(String username, String password) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		TypedQuery<Utilizador> query = entityManager.createQuery(
				"SELECT u FROM Utilizador u WHERE u.username = :username AND u.password = :password", Utilizador.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<Utilizador> resultList = query.getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	public Long count() {
		return entityManager.createQuery("SELECT COUNT(u) FROM Utilizador u", Long.class).getSingleResult();
	}

}
