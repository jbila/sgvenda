package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.Parcela;
import com.meldev.utils.JpaUtil;

public class ParcelaDao implements GenericDao<Parcela> {

	private final EntityManager entityManager;

	public ParcelaDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Parcela save(Parcela Parcela) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if(Parcela.getId()!=null)
			entityManager.merge(Parcela);
			else
				entityManager.persist(Parcela);
		transaction.commit();
		return Parcela;
	}

	@Override
	public List<Parcela> saveAll(List<Parcela> Parcelas) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Parcelas.forEach(Parcela -> entityManager.persist(Parcela));
		transaction.commit();
		return Parcelas;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Parcela Parcela = entityManager.find(Parcela.class, id);
		if (Parcela != null) {
			entityManager.remove(Parcela);
		}
		transaction.commit();
	}

	@Override
	public List<Parcela> findAll(Long empresaId) {
		TypedQuery<Parcela> query = entityManager.createQuery("SELECT c FROM Parcela c where c.empresa.id=:empresaId", Parcela.class);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<Parcela> findByName(String nome) {
		TypedQuery<Parcela> query = entityManager.createQuery("SELECT c FROM Parcela c WHERE c.nome = :nome",
				Parcela.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	@Override
	public List<Parcela> findAll(String nome,Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	public double getValor(int parseInt) {
		// TODO Auto-generated method stub
		return 0;
	}

}
