package com.meldev.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.meldev.model.Empresa;
import com.meldev.utils.JpaUtil;
import javax.persistence.Query;

public class EmpresaDao implements GenericDao<Empresa> {

	private final EntityManager entityManager;

	public EmpresaDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public Empresa save(Empresa Empresa) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (Empresa.getId() != null)
			entityManager.merge(Empresa);
		else
			entityManager.persist(Empresa);

		transaction.commit();
		return Empresa;
	}

	//@Override
	public List<Empresa> saveAll(List<Empresa> Empresas) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Empresas.forEach(Empresa -> entityManager.persist(Empresa));
		transaction.commit();
		return Empresas;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Empresa Empresa = entityManager.find(Empresa.class, id);
		if (Empresa != null) {
			entityManager.remove(Empresa);
		}
		transaction.commit();
	}

	//@Override
	public List<Empresa> findAll() {
		TypedQuery<Empresa> query = entityManager.createQuery("SELECT c FROM Empresa c", Empresa.class);
		return query.getResultList();
	}

	public List<Empresa> findByName(String nome) {
		TypedQuery<Empresa> query = entityManager.createQuery("SELECT c FROM Empresa c WHERE c.nome = :nome",
				Empresa.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	public List<Empresa> findAll(String nome) {
		TypedQuery<Empresa> query;
		if (nome != null && !nome.isEmpty()) {
			query = entityManager.createQuery("SELECT e FROM Empresa e WHERE e.nome = :nome", Empresa.class);
			query.setParameter("nome", nome);
		} else {
			query = entityManager.createQuery("SELECT e FROM Empresa e", Empresa.class);
		}
		return query.getResultList();
	}

	public Optional<Empresa> findEmpresa() {
		TypedQuery<Empresa> query = entityManager.createQuery("SELECT e FROM Empresa e", Empresa.class);
		try {
			Empresa empresa = query.getSingleResult();
			return Optional.of(empresa);
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<Empresa> findAll(Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Empresa> findAll(String nome, Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

   public long count() {
        Query query = entityManager.createQuery("SELECT COUNT(e) FROM Empresa e");
        return (long) query.getSingleResult();
    }

     public Empresa findById(long id) {
        return entityManager.find(Empresa.class, id);
    }

}
