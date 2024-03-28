package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.Provincia;
import com.meldev.utils.JpaUtil;

public class ProvinciaDao implements GenericDao<Provincia> {

	private final EntityManager entityManager;

	public ProvinciaDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Provincia save(Provincia provincia) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (provincia.getId() != null)
			entityManager.merge(provincia);
		else
			entityManager.persist(provincia);
		transaction.commit();
		return provincia;
	}

	@Override
	public List<Provincia> saveAll(List<Provincia> provincias) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		provincias.forEach(Provincia -> entityManager.persist(Provincia));
		transaction.commit();
		return provincias;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Provincia Provincia = entityManager.find(Provincia.class, id);
		if (Provincia != null) {
			entityManager.remove(Provincia);
		}
		transaction.commit();
	}

	public List<Provincia> findAll() {
		TypedQuery<Provincia> query = entityManager.createQuery("SELECT c FROM Provincia c", Provincia.class);
		return query.getResultList();
	}

	public List<Provincia> findByName(String nome) {
		TypedQuery<Provincia> query = entityManager.createQuery("SELECT c FROM Provincia c WHERE c.nome = :nome",
				Provincia.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	public List<Provincia> findAll(String nome) {
		TypedQuery<Provincia> query = entityManager.createQuery("SELECT p FROM Provincia p WHERE p.nome = :nome",
				Provincia.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	@Override
	public List<Provincia> findAll(Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Provincia> findAll(String nome, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

}
