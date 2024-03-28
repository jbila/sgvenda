package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.Departamento;
import com.meldev.utils.JpaUtil;

public class DepartamentoDao implements GenericDao<Departamento> {

	private final EntityManager entityManager;

	public DepartamentoDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Departamento save(Departamento Departamento) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if(Departamento.getId()!=null)
			entityManager.merge(Departamento);

		else
			entityManager.persist(Departamento);
		transaction.commit();
		return Departamento;
	}

	@Override
	public List<Departamento> saveAll(List<Departamento> Departamentos) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Departamentos.forEach(Departamento -> entityManager.persist(Departamento));
		transaction.commit();
		return Departamentos;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Departamento Departamento = entityManager.find(Departamento.class, id);
		if (Departamento != null) {
			entityManager.remove(Departamento);
		}
		transaction.commit();
	}

	@Override
	public List<Departamento> findAll(Long empresaId) {
		TypedQuery<Departamento> query = entityManager.createQuery("SELECT d FROM Departamento d where d.empresa.id=:empresaId", Departamento.class);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<Departamento> findByName(String nome) {
		TypedQuery<Departamento> query = entityManager.createQuery("SELECT d FROM Departamento d WHERE d.nome = :nome",
				Departamento.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	@Override
	public List<Departamento> findAll(String nome,Long empresaId) {
		TypedQuery<Departamento> query = entityManager.createQuery("SELECT d FROM Departamento d WHERE d.nome LIKE :nome AND d.empresa.id=:empresaId ",
				Departamento.class);
		 query.setParameter("nome", "%" + nome + "%");
		 query.setParameter("empresaId", "empresaId");
		return query.getResultList();
		
	}




}
