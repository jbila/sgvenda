package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.Patrimonio;
import com.meldev.utils.JpaUtil;

public class PatrimonioDao implements GenericDao<Patrimonio> {

	private final EntityManager entityManager;

	public PatrimonioDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Patrimonio save(Patrimonio Patrimonio) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if(Patrimonio.getId()!=null)
			entityManager.merge(Patrimonio);
			else
				entityManager.persist(Patrimonio);
		transaction.commit();
		return Patrimonio;
	}

	@Override
	public List<Patrimonio> saveAll(List<Patrimonio> Patrimonios) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Patrimonios.forEach(Patrimonio -> entityManager.persist(Patrimonio));
		transaction.commit();
		return Patrimonios;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Patrimonio Patrimonio = entityManager.find(Patrimonio.class, id);
		if (Patrimonio != null) {
			entityManager.remove(Patrimonio);
		}
		transaction.commit();
	}

	@Override
	public List<Patrimonio> findAll(Long empresaId) {
		TypedQuery<Patrimonio> query = entityManager.createQuery("SELECT c FROM Patrimonio c where c.empresa.id=:empresaId", Patrimonio.class);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<Patrimonio> findByName(String nome) {
		TypedQuery<Patrimonio> query = entityManager.createQuery("SELECT c FROM Patrimonio c WHERE c.nome = :nome",
				Patrimonio.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	@Override
	public List<Patrimonio> findAll(String nome,Long empresaId) {
		TypedQuery<Patrimonio> query = entityManager.createQuery("SELECT c FROM Patrimonio c WHERE c.descricao like:nome AND c.empresa.id=:empresaId",
				Patrimonio.class);
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}
}
