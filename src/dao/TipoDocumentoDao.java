package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.TipoDocumento;
import com.meldev.utils.JpaUtil;

public class TipoDocumentoDao implements GenericDao<TipoDocumento> {

	private final EntityManager entityManager;

	public TipoDocumentoDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public TipoDocumento save(TipoDocumento TipoDocumento) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if(TipoDocumento.getId()!=null)
		entityManager.merge(TipoDocumento);
		else
			entityManager.persist(TipoDocumento);

		transaction.commit();
		return TipoDocumento;
	}

	@Override
	public List<TipoDocumento> saveAll(List<TipoDocumento> TipoDocumentos) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		TipoDocumentos.forEach(TipoDocumento -> entityManager.persist(TipoDocumento));
		transaction.commit();
		return TipoDocumentos;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		TipoDocumento TipoDocumento = entityManager.find(TipoDocumento.class, id);
		if (TipoDocumento != null) {
			entityManager.remove(TipoDocumento);
		}
		transaction.commit();
	}

	@Override
	public List<TipoDocumento> findAll(Long empresaId) {
		TypedQuery<TipoDocumento> query = entityManager.createQuery("SELECT c FROM TipoDocumento c where c.empresa.id=:empresaId", TipoDocumento.class).setMaxResults(15);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<TipoDocumento> findByName(String nome) {
		TypedQuery<TipoDocumento> query = entityManager.createQuery("SELECT c FROM TipoDocumento c WHERE c.nome = :nome",
				TipoDocumento.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	@Override
	public List<TipoDocumento> findAll(String nome,Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

}
