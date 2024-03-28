package com.meldev.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.Pedido;
import com.meldev.utils.JpaUtil;

public class PedidoDao implements GenericDao<Pedido> {

	private final EntityManager entityManager;

	public PedidoDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Pedido save(Pedido pedido) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (pedido.getId() != null)
			entityManager.merge(pedido);
		else
			entityManager.persist(pedido);
		transaction.commit();
		return pedido;
	}

	@Override
	public List<Pedido> saveAll(List<Pedido> pedidos) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		pedidos.forEach(Pedido -> entityManager.persist(Pedido));
		transaction.commit();
		return pedidos;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Pedido pedido = entityManager.find(Pedido.class, id);
		if (pedido != null) {
			entityManager.remove(pedido);
		}
		transaction.commit();
	}

	@Override
	public List<Pedido> findAll(Long empresaId) {
		TypedQuery<Pedido> query = entityManager.createQuery("SELECT p FROM Pedido p where p.empresa.id=:empresaId", Pedido.class)
				.setMaxResults(25);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<Pedido> findByName(String nome) {
		TypedQuery<Pedido> query = entityManager.createQuery("SELECT p FROM Pedido p WHERE p.nome = :nome",
				Pedido.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	@Override
	public List<Pedido> findAll(String nome,Long empresaId) {
		TypedQuery<Pedido> query = entityManager.createQuery("SELECT p FROM Pedido p WHERE p.cliente.nome = :nome AND p.empresa.id=:empresaId",
				Pedido.class);
		query.setParameter("nome", nome);
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<Pedido> findAllByDates(LocalDate startDate, LocalDate endDate) {
		TypedQuery<Pedido> query = entityManager.createQuery(
	            "SELECT p FROM Pedido p WHERE p.created BETWEEN :startDate AND :endDate", Pedido.class);
	    query.setParameter("startDate", startDate);
	    query.setParameter("endDate", endDate);
	    return query.getResultList();
	}

	public List<Pedido> search(String text) {
		TypedQuery<Pedido> query = entityManager.createQuery(
				"SELECT p FROM Pedido p WHERE p.documento.nome = :nome or p.cliente.nome=:nome", Pedido.class);
		query.setParameter("nome", text);
		return query.getResultList();
	}

	public List<Pedido> findByDates(LocalDate dateStart, LocalDate dateEnd) {
		TypedQuery<Pedido> query = entityManager
				.createQuery("SELECT p FROM Pedido p WHERE p.created BETWEEN :start AND :end", Pedido.class);
		query.setParameter("start", dateStart);
		query.setParameter("end", dateEnd);
		return query.getResultList();
	}

	// --------------------transaction-------------------
	public void beginTransaction() {
		entityManager.getTransaction().begin();
	}

	public void rollbackTransaction() {
		EntityTransaction transaction = entityManager.getTransaction();
		if (transaction.isActive()) {
			transaction.rollback();
		}
	}

	public void commitTransaction() {
		EntityTransaction transaction = entityManager.getTransaction();
		if (transaction.isActive()) {
			transaction.commit();
		}
	}

}
