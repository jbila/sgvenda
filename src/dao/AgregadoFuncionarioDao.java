package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.AgregadoFuncionario;
import com.meldev.utils.JpaUtil;

public class AgregadoFuncionarioDao implements GenericDao<AgregadoFuncionario> {

	private final EntityManager entityManager;

	public AgregadoFuncionarioDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public AgregadoFuncionario save(AgregadoFuncionario AgregadoFuncionario) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (AgregadoFuncionario.getId() != null) {
			entityManager.merge(AgregadoFuncionario);
			
		} else {
			entityManager.persist(AgregadoFuncionario);
			
			}
		transaction.commit();

		
		return AgregadoFuncionario;
	}

	@Override
	public List<AgregadoFuncionario> saveAll(List<AgregadoFuncionario> AgregadoFuncionarios) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		AgregadoFuncionarios.forEach(AgregadoFuncionario -> entityManager.persist(AgregadoFuncionario));
		transaction.commit();
		return AgregadoFuncionarios;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		AgregadoFuncionario AgregadoFuncionario = entityManager.find(AgregadoFuncionario.class, id);
		if (AgregadoFuncionario != null) {
			entityManager.remove(AgregadoFuncionario);
		}
		transaction.commit();
	}

	@Override
	public List<AgregadoFuncionario> findAll(Long empresaId) {
		TypedQuery<AgregadoFuncionario> query = entityManager.createQuery("SELECT c FROM AgregadoFuncionario c where c.empresa.id = :empresaId",
				AgregadoFuncionario.class);
		return query.getResultList();
	}

	public List<AgregadoFuncionario> findByName(String nome) {
		TypedQuery<AgregadoFuncionario> query = entityManager
				.createQuery("SELECT c FROM AgregadoFuncionario c WHERE c.nome = :nome", AgregadoFuncionario.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	@Override
	public List<AgregadoFuncionario> findAll(String nome,Long empresaId) {
		TypedQuery<AgregadoFuncionario> query = entityManager
				.createQuery("SELECT c FROM AgregadoFuncionario c WHERE c.nome = :nome AND c.empresa.id = :empresaId", AgregadoFuncionario.class);
		query.setParameter("nome", nome);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}
	
	public List<AgregadoFuncionario> findAgregadoByFuncionarioId(Long id) {
		TypedQuery<AgregadoFuncionario> query = entityManager
				.createQuery("SELECT c FROM AgregadoFuncionario c WHERE funcionario.id = :id", AgregadoFuncionario.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	


}
