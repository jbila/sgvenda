package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.TipoDocumentoItems;
import com.meldev.utils.JpaUtil;

public class TipoDocumentoItemsDao implements GenericDao<TipoDocumentoItems> {

	private final EntityManager entityManager;

	public TipoDocumentoItemsDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public TipoDocumentoItems save(TipoDocumentoItems TipoDocumentoItems) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if(TipoDocumentoItems.getId()!=null)
		entityManager.merge(TipoDocumentoItems);
		else
			entityManager.persist(TipoDocumentoItems);

		transaction.commit();
		return TipoDocumentoItems;
	}

	@Override
	public List<TipoDocumentoItems> saveAll(List<TipoDocumentoItems> TipoDocumentoItemss) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		TipoDocumentoItemss.forEach(TipoDocumentoItems -> entityManager.persist(TipoDocumentoItems));
		transaction.commit();
		return TipoDocumentoItemss;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		TipoDocumentoItems TipoDocumentoItems = entityManager.find(TipoDocumentoItems.class, id);
		if (TipoDocumentoItems != null) {
			entityManager.remove(TipoDocumentoItems);
		}
		transaction.commit();
	}

	@Override
	public List<TipoDocumentoItems> findAll(Long empresaId) {
		TypedQuery<TipoDocumentoItems> query = entityManager.createQuery("SELECT c FROM TipoDocumentoItems c where c.empresa.id=:empresaId", TipoDocumentoItems.class);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<TipoDocumentoItems> findByIdPedido(Long idPedido) {
		TypedQuery<TipoDocumentoItems> query = entityManager.createQuery("SELECT c FROM TipoDocumentoItems c WHERE c.tipoDocumento.id = :id_pedido",
				TipoDocumentoItems.class);
		query.setParameter("id_pedido", idPedido);
		return query.getResultList();
	}

	@Override
	public List<TipoDocumentoItems> findAll(String nome,Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
