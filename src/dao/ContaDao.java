package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.Conta;
import com.meldev.utils.JpaUtil;

public class ContaDao implements GenericDao<Conta> {

	private final EntityManager entityManager;

	public ContaDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Conta save(Conta conta) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (conta.getId() != null)
			entityManager.merge(conta);
		else
			entityManager.persist(conta);
		transaction.commit();
		return conta;
	}

	@Override
	public List<Conta> saveAll(List<Conta> Contas) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Contas.forEach(Conta -> entityManager.persist(Conta));
		transaction.commit();
		return Contas;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Conta Conta = entityManager.find(Conta.class, id);
		if (Conta != null) {
			entityManager.remove(Conta);
		}
		transaction.commit();
	}

	@Override
	public List<Conta> findAll (Long empresaId) {
		TypedQuery<Conta> query = entityManager.createQuery("SELECT c FROM Conta c where c.empresa.id = :empresaId", Conta.class);
	    query.setParameter("empresaId", empresaId);

		return query.getResultList();
	}


	public List<Conta> findByName(String nome,Long empresaId) {
		TypedQuery<Conta> query = entityManager.createQuery("SELECT c FROM Conta c WHERE c.nome = :nome AND c.empresa.id = :empresaId ", Conta.class);
		query.setParameter("nome", nome);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	@Override
	public List<Conta> findAll(String nome,Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

}
