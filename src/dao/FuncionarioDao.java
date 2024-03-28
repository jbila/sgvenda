package com.meldev.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.meldev.model.Funcionario;
import com.meldev.utils.JpaUtil;

public class FuncionarioDao implements GenericDao<Funcionario> {

	private final EntityManager entityManager;

	public FuncionarioDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Funcionario save(Funcionario funcionario) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (funcionario.getId() != null)
			entityManager.merge(funcionario);
		else
			entityManager.persist(funcionario);
		transaction.commit();
		return funcionario;
	}

	@Override
	public List<Funcionario> saveAll(List<Funcionario> Funcionarios) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Funcionarios.forEach(Funcionario -> entityManager.persist(Funcionario));
		transaction.commit();
		return Funcionarios;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Funcionario Funcionario = entityManager.find(Funcionario.class, id);
		if (Funcionario != null) {
			entityManager.remove(Funcionario);
		}
		transaction.commit();
	}

	@Override
	public List<Funcionario> findAll(Long empresaId) {
		TypedQuery<Funcionario> query = entityManager.createQuery("SELECT f FROM Funcionario f where f.empresa.id=:empresaId", Funcionario.class);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<Funcionario> findByName(String nome) {
		TypedQuery<Funcionario> query = entityManager.createQuery("SELECT c FROM Funcionario c WHERE c.nome = :nome",
				Funcionario.class);
		query.setParameter("nome", nome);
		
		return query.getResultList();
	}

	@Override
	public List<Funcionario> findAll(String nome,Long empresaId) {
		TypedQuery<Funcionario> query = entityManager.createQuery("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.empresa.id=:empresaId",
				Funcionario.class);
		query.setParameter("nome", nome);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}
	public long countPureQuery() {
		String sql = "SELECT COUNT(*) FROM tbl_funcionario";
		Query query = entityManager.createNativeQuery(sql);
		Object result = query.getSingleResult();
		if (result instanceof BigInteger) {
			return ((BigInteger) result).longValue();
		} else {
			return (Long) result;
		}
	}

//--
	public long count() {
		return entityManager.createQuery("SELECT COUNT(f) FROM Funcionario f", Long.class).getSingleResult();
	}

}
