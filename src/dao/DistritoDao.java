package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.Distrito;
import com.meldev.utils.JpaUtil;

public class DistritoDao implements GenericDao<Distrito> {

	private final EntityManager entityManager;

	public DistritoDao() {
		// super(Distrito.class);
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Distrito save(Distrito Distrito) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if(Distrito.getId()!=null)
		entityManager.merge(Distrito);
		else
			entityManager.persist(Distrito);
		transaction.commit();
		return Distrito;
	}

	@Override
	public List<Distrito> saveAll(List<Distrito> Distritos) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Distritos.forEach(Distrito -> entityManager.persist(Distrito));
		transaction.commit();
		return Distritos;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Distrito Distrito = entityManager.find(Distrito.class, id);
		if (Distrito != null) {
			entityManager.remove(Distrito);
		}
		transaction.commit();
	}

	public List<Distrito> getDistritosByIdProvincia(String nome) {
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		TypedQuery<Distrito> query = entityManager
				.createQuery("SELECT d FROM Distrito d WHERE d.provincia.nome = :nome", Distrito.class);
		query.setParameter("nome", nome);
		
		List<Distrito> distritos = query.getResultList();
		transaction.commit();
		return distritos;
	}

	//@Override
	public List<Distrito> findAll() {
		TypedQuery<Distrito> query = entityManager.createQuery("SELECT c FROM Distrito c", Distrito.class);
		return query.getResultList();
	}

	public List<Distrito> findByProvinciaId(Long provinciaId) {
		TypedQuery<Distrito> query = entityManager.createQuery("SELECT d FROM Distrito d WHERE d.provincia.id=:provinciaId",
				Distrito.class);
		query.setParameter("provinciaId", provinciaId);
		return query.getResultList();
	}

	//@Override
	public List<Distrito> findAll(String nome) {
		String jpql = "SELECT d FROM Distrito d WHERE d.nome=:nome";
	    TypedQuery<Distrito> query = entityManager.createQuery(jpql, Distrito.class);
	    query.setParameter("nome", nome);
	    List<Distrito> distritos = query.getResultList();
	    return distritos;
	}

	public List<Distrito> findAllByProvinciaName(Long id) {
		String jpql = "SELECT d FROM Distrito d WHERE d.provincia.id=:id";
	    TypedQuery<Distrito> query = entityManager.createQuery(jpql, Distrito.class);
	    query.setParameter("id", id);
	    List<Distrito> distritos = query.getResultList();
	    return distritos;
	}

	@Override
	public List<Distrito> findAll(Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Distrito> findAll(String nome, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}
}
