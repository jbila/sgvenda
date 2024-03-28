package com.meldev.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.Despesa;
import com.meldev.utils.JpaUtil;

public class DespesaDao implements GenericDao<Despesa> {

	private final EntityManager entityManager;

	public DespesaDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Despesa save(Despesa Despesa) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (Despesa.getId() != null)
			entityManager.merge(Despesa);
		else
			entityManager.persist(Despesa);

		transaction.commit();
		return Despesa;
	}

	@Override
	public List<Despesa> saveAll(List<Despesa> Despesas) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Despesas.forEach(Despesa -> entityManager.persist(Despesa));
		transaction.commit();
		return Despesas;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Despesa Despesa = entityManager.find(Despesa.class, id);
		if (Despesa != null) {
			entityManager.remove(Despesa);
		}
		transaction.commit();
	}

	@Override
	public List<Despesa> findAll(Long empresaId) {
		TypedQuery<Despesa> query = entityManager.createQuery("SELECT d FROM Despesa d where d.empresa.id=:empresaId",
				Despesa.class);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<Despesa> findByName(String nome) {
		TypedQuery<Despesa> query = entityManager.createQuery("SELECT d FROM Despesa d WHERE d.nome = :nome",
				Despesa.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	@Override
	public List<Despesa> findAll(String nome, Long empresaId) {
		TypedQuery<Despesa> query = entityManager.createQuery(
				"SELECT d FROM Despesa d WHERE d.mesReferente LIKE :nome OR ano LIKE :nome AND d.empresa.id=:empresaId ",
				Despesa.class);
		query.setParameter("nome", "%" + nome + "%");
		query.setParameter("empresaId", empresaId);
		return query.getResultList();

	}

	public List<Despesa> findAllByDates(LocalDate dataInicio, LocalDate dataFinal) {
		TypedQuery<Despesa> query = entityManager
				.createQuery("SELECT c FROM Despesa c WHERE c.created BETWEEN :data_i AND :data_f", Despesa.class);
		query.setParameter("data_i", dataInicio);
		query.setParameter("data_f", dataFinal);
		return query.getResultList();
	}

	public List<Despesa> findByAno(Integer ano, Long empresaId) {
		TypedQuery<Despesa> query = entityManager
				.createQuery("SELECT d FROM Despesa d WHERE d.ano = :ano AND d.empresa.id=:empresaId", Despesa.class);
		query.setParameter("ano", ano);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<Despesa> findByAnoAndMes(Integer ano, String mes, Long empresaId) {
		TypedQuery<Despesa> query = entityManager.createQuery(
				"SELECT d FROM Despesa d WHERE d.ano = :ano AND d.mesReferente=:mes AND d.empresa.id=:empresaId",
				Despesa.class);
		query.setParameter("ano", ano);
		query.setParameter("mes", mes);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

}
