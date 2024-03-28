package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.Fornecedor;
import com.meldev.utils.JpaUtil;

public class FornecedorDao implements GenericDao<Fornecedor> {

	private final EntityManager entityManager;

	public FornecedorDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Fornecedor save(Fornecedor Fornecedor) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (Fornecedor.getId() != null)
			entityManager.merge(Fornecedor);
		else
			entityManager.persist(Fornecedor);
		transaction.commit();
		return Fornecedor;
	}

	@Override
	public List<Fornecedor> saveAll(List<Fornecedor> Fornecedors) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Fornecedors.forEach(Fornecedor -> entityManager.persist(Fornecedor));
		transaction.commit();
		return Fornecedors;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Fornecedor Fornecedor = entityManager.find(Fornecedor.class, id);
		if (Fornecedor != null) {
			entityManager.remove(Fornecedor);
		}
		transaction.commit();
	}

	@Override
	public List<Fornecedor> findAll(Long empresaId) {
		TypedQuery<Fornecedor> query = entityManager.createQuery("SELECT c FROM Fornecedor c where c.empresa.id=:empresaId", Fornecedor.class);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<Fornecedor> findByName(String nome) {
		TypedQuery<Fornecedor> query = entityManager.createQuery("SELECT c FROM Fornecedor c WHERE c.nome = :nome",
				Fornecedor.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	@Override
	public List<Fornecedor> findAll(String nome,Long empresaId) {
		TypedQuery<Fornecedor> query = entityManager.createQuery("SELECT c FROM Fornecedor c WHERE c.nome LIKE :nome AND c.empresa.id=:empresaId",
				Fornecedor.class);
		query.setParameter("nome", "%" + nome + "%");
		 query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public Fornecedor findById(long id) {
		TypedQuery<Fornecedor> query = entityManager.createQuery("SELECT c FROM Fornecedor c WHERE c.id= :id",
				Fornecedor.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}
}
