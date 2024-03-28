package com.meldev.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import com.meldev.model.Documento;
import com.meldev.utils.JpaUtil;

public class DocumentoDao implements GenericDao<Documento> {

	private final EntityManager entityManager;

	public DocumentoDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Documento save(Documento Documento) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (Documento.getId() != null)
			entityManager.merge(Documento);
		else
			entityManager.persist(Documento);
		transaction.commit();
		return Documento;
	}

	@Override
	public List<Documento> saveAll(List<Documento> Documentos) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Documentos.forEach(Documento -> entityManager.persist(Documento));
		transaction.commit();
		return Documentos;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Documento Documento = entityManager.find(Documento.class, id);
		if (Documento != null) {
			entityManager.remove(Documento);
		}
		transaction.commit();
	}

	@Override
	public List<Documento> findAll(Long empresaId) {
		TypedQuery<Documento> query = entityManager.createQuery("SELECT c FROM Documento c where c.empresa.id=:empresaId", Documento.class);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public Optional<Documento> findByName(String nome) {
		TypedQuery<Documento> query = entityManager.createQuery("SELECT c FROM Documento c WHERE c.nome = :nome",
				Documento.class);
		query.setParameter("nome", nome);
		try {
			return Optional.ofNullable(query.getSingleResult());
		} catch (NoResultException | NonUniqueResultException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<Documento> findAll(String nome,Long empresaId) {
		TypedQuery<Documento> query = entityManager.createQuery("SELECT c FROM Documento c WHERE c.nome = :nome AND c.empresa.id=:empresaId",
				Documento.class);
		query.setParameter("nome", nome);
		 query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

}
