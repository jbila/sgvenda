package com.meldev.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.meldev.model.Cliente;
import com.meldev.utils.JpaUtil;

public class ClienteDao implements GenericDao<Cliente> {

	private final EntityManager entityManager;

	public ClienteDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	
	public Cliente save2(Cliente cliente) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (cliente.getId() != null)
			entityManager.merge(cliente);
		else
			entityManager.persist(cliente);

		transaction.commit();
		return cliente;
	}
	@Override
	public Cliente save(Cliente cliente) {
	    EntityTransaction transaction = entityManager.getTransaction();
	    try {
	        transaction.begin();
	        if (cliente.getId() != null)
	            entityManager.merge(cliente);
	        else
	            entityManager.persist(cliente);

	        transaction.commit();
	        return cliente;
	    } catch (PersistenceException e) {
	        Throwable cause = e.getCause();
	        if (cause instanceof SQLIntegrityConstraintViolationException) {
	            // Handle the duplicate entry exception here
	            // You can log an error message, return an error response, or perform other actions
	            // For example:
	            // logger.error("Duplicate entry error: " + cause.getMessage());
	            // return new ErrorResponse("Duplicate entry error");
	            
	            // Alternatively, you can rethrow the exception or handle it differently based on your requirements.
	            throw e;
	        } else {
	            // Handle other types of exceptions as needed
	            // For example, you can log and rethrow them or return a generic error response.
	            // logger.error("Error occurred: " + e.getMessage());
	            // throw new CustomException("An error occurred");
	        }
	    } finally {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	    }
		return cliente;
	}


	@Override
	public List<Cliente> saveAll(List<Cliente> Clientes) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Clientes.forEach(Cliente -> entityManager.persist(Cliente));
		transaction.commit();
		return Clientes;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Cliente Cliente = entityManager.find(Cliente.class, id);
		if (Cliente != null) {
			entityManager.remove(Cliente);
		}
		transaction.commit();
	}

	@Override
	public List<Cliente> findAll(Long empresaId) {
		TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c where c.empresa.id = :empresaId", Cliente.class);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<Cliente> findByName(String nome) {
		TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.nome LIKE :nome",
				Cliente.class);
		query.setParameter("nome", "%" + nome + "%");
		return query.getResultList();
	}

	@Override
	public List<Cliente> findAll(String nome,Long empresaId) {
		String jpql = "SELECT c FROM Cliente c WHERE c.nome LIKE:nome OR c.telefone LIKE:nome OR c.codigo LIKE:nome AND c.empresa.id = :empresaId";
		TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
		query.setParameter("nome", "%" + nome + "%");
		query.setParameter("empresaId", empresaId);

		List<Cliente> clientes = query.getResultList();
		return clientes;

	}

	public Optional<Cliente> getByCodigoOrNomee(String text) {
		TypedQuery<Cliente> query = entityManager.createQuery(
				"SELECT c FROM Cliente c WHERE c.nome = :nome or c.codigo=:nome or c.telefone=:nome", Cliente.class);
		query.setParameter("nome", text);
		return Optional.ofNullable(query.getSingleResult());
	}

	/**
	 * Retrieves a Cliente by matching either the nome, codigo, or telefone.
	 *
	 * @param searchText The text to search for in the nome, codigo, or telefone
	 *                   fields.
	 * @return An Optional containing the found Cliente, or an empty Optional if not
	 *         found.
	 */
	public Optional<Cliente> findByClienteCodigoOrNome(String codigoInput) {
		TypedQuery<Cliente> query = entityManager
				.createQuery("SELECT c FROM Cliente c WHERE c.codigo=:codigoInput OR c.nome=:codigoInput", Cliente.class);
		query.setParameter("codigoInput", codigoInput);

		try {
			return Optional.ofNullable(query.getSingleResult());
		} catch (NoResultException | NonUniqueResultException e) {
			return Optional.empty();
		}
	}

	public long count() {
		return entityManager.createQuery("SELECT COUNT(c) FROM Cliente c", Long.class).getSingleResult();
	}

}
