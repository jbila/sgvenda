package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.DocumentoFuncionario;
import com.meldev.utils.JpaUtil;

public class DocumentoFuncionarioDao implements GenericDao<DocumentoFuncionario> {

	private final EntityManager entityManager;

	public DocumentoFuncionarioDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public DocumentoFuncionario save(DocumentoFuncionario DocumentoFuncionario) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if(DocumentoFuncionario.getId()!=null)
		entityManager.merge(DocumentoFuncionario);
		else
			entityManager.persist(DocumentoFuncionario);

		transaction.commit();
		return DocumentoFuncionario;
	}

	@Override
	public List<DocumentoFuncionario> saveAll(List<DocumentoFuncionario> DocumentoFuncionarios) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		DocumentoFuncionarios.forEach(DocumentoFuncionario -> entityManager.persist(DocumentoFuncionario));
		transaction.commit();
		return DocumentoFuncionarios;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		DocumentoFuncionario DocumentoFuncionario = entityManager.find(DocumentoFuncionario.class, id);
		if (DocumentoFuncionario != null) {
			entityManager.remove(DocumentoFuncionario);
		}
		transaction.commit();
	}

	@Override
	public List<DocumentoFuncionario> findAll(Long empresaId) {
		TypedQuery<DocumentoFuncionario> query = entityManager.createQuery("SELECT c FROM DocumentoFuncionario c where c.empresa.id=:empresaId", DocumentoFuncionario.class);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<DocumentoFuncionario> findByName(String nome) {
		TypedQuery<DocumentoFuncionario> query = entityManager.createQuery("SELECT c FROM DocumentoFuncionario c WHERE c.nome = :nome",
				DocumentoFuncionario.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	@Override
	public List<DocumentoFuncionario> findAll(String nome,Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DocumentoFuncionario> findDocumentosByFuncionarioId(Long id) {
			TypedQuery<DocumentoFuncionario> query = entityManager.createQuery("SELECT c FROM DocumentoFuncionario c WHERE funcionario.id = :id",
					DocumentoFuncionario.class);
			query.setParameter("id", id);
			return query.getResultList();
		}
	
	

}
