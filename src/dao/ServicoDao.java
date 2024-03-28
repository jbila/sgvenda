package com.meldev.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.Servico;
import com.meldev.utils.JpaUtil;

public class ServicoDao implements GenericDao<Servico> {

	private final EntityManager entityManager;

	public ServicoDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Servico save(Servico Servico) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (Servico.getId() != null)
			entityManager.merge(Servico);
		else
			entityManager.persist(Servico);
		transaction.commit();
		return Servico;
	}

	@Override
	public List<Servico> saveAll(List<Servico> Servicos) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Servicos.forEach(Servico -> entityManager.persist(Servico));
		transaction.commit();
		return Servicos;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Servico Servico = entityManager.find(Servico.class, id);
		if (Servico != null) {
			entityManager.remove(Servico);
		}
		transaction.commit();
	}

	@Override
	public List<Servico> findAll(Long empresaId) {
		TypedQuery<Servico> query = entityManager.createQuery("SELECT c FROM Servico c where c.empresa.id=:empresaId", Servico.class);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<Servico> findByName(String nome) {
		TypedQuery<Servico> query = entityManager.createQuery("SELECT c FROM Servico c WHERE c.nome = :nome",
				Servico.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	@Override
	public List<Servico> findAll(String codigo,Long empresaId) {
		TypedQuery<Servico> query = entityManager
				.createQuery("SELECT c FROM Servico c WHERE c.codigo LIKE CONCAT(:codigo,'%')", Servico.class);
		query.setParameter("codigo", codigo);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public Servico getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Servico> getOneServico(String inputCodigo) {
		TypedQuery<Servico> query = entityManager.createQuery("SELECT c FROM Servico c WHERE c.codigo = :codigo",
				Servico.class);
		query.setParameter("codigo", inputCodigo);
		return Optional.ofNullable(query.getSingleResult());
	}

}
