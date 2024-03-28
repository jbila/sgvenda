package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.FormasDePagamento;
import com.meldev.utils.JpaUtil;

public class FormaDePagamentoDao implements GenericDao<FormasDePagamento> {

	private final EntityManager entityManager;

	public FormaDePagamentoDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public FormasDePagamento save(FormasDePagamento FormaDePagamento) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (FormaDePagamento.getId() != null)
			entityManager.merge(FormaDePagamento);
		else
			entityManager.persist(FormaDePagamento);
		transaction.commit();
		return FormaDePagamento;
	}

	@Override
	public List<FormasDePagamento> saveAll(List<FormasDePagamento> FormaDePagamentos) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		FormaDePagamentos.forEach(FormaDePagamento -> entityManager.persist(FormaDePagamento));
		transaction.commit();
		return FormaDePagamentos;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		FormasDePagamento FormaDePagamento = entityManager.find(FormasDePagamento.class, id);
		if (FormaDePagamento != null) {
			entityManager.remove(FormaDePagamento);
		}
		transaction.commit();
	}

	@Override
	public List<FormasDePagamento> findAll(Long empresaId) {
		TypedQuery<FormasDePagamento> query = entityManager.createQuery("SELECT c FROM FormasDePagamento c where c.empresa.id=:empresaId",
				FormasDePagamento.class);
	    query.setParameter("empresaId", empresaId);

		return query.getResultList();
	}

	public List<FormasDePagamento> findByName(String nome,Long empresaId) {
		TypedQuery<FormasDePagamento> query = entityManager
				.createQuery("SELECT c FROM FormaDePagamento c WHERE c.nome = :nome AND c.empresa.id=:empresaId", FormasDePagamento.class);
		query.setParameter("nome", nome);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	@Override
	public List<FormasDePagamento> findAll(String nome,Long empresaId  ) {
		TypedQuery<FormasDePagamento> query = entityManager
				.createQuery("SELECT c FROM FormaDePagamento c WHERE c.nome LIKE :nome AND c.empresa.id=:empresaId", FormasDePagamento.class);
		query.setParameter("nome", "%" + nome + "%");
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

}
