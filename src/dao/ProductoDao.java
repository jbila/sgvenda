package com.meldev.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import model.Producto;
import com.meldev.utils.JpaUtil;

public class ProductoDao implements GenericDao<Producto> {

    private final EntityManager entityManager;

    public ProductoDao() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    //@Override
    public Producto save3(Producto producto) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        if (producto.getId() != null) {
            entityManager.merge(producto);
        } else {
            entityManager.persist(producto);
        }
        transaction.commit();
        return producto;
    }

    @Override
    public Producto save(Producto producto) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            if (producto.getId() != null) {
                entityManager.merge(producto);
            } else {
                entityManager.persist(producto);
            }
            transaction.commit();
            return producto;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenceException("Failed to save Producto: " + e.getMessage(), e);
        }
    }

    public Producto save2(Producto producto) {
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            if (producto.getId() != null) {
                producto = entityManager.merge(producto);
            } else {
                entityManager.persist(producto);
            }

            transaction.commit();
            return producto;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            // Handle or rethrow the exception as needed
            throw new PersistenceException("Failed to save Producto: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Producto> saveAll(List<Producto> Productos) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Productos.forEach(Producto -> entityManager.persist(Producto));
        transaction.commit();
        return Productos;
    }

    @Override
    public void deleteById(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Producto Producto = entityManager.find(Producto.class, id);
        if (Producto != null) {
            entityManager.remove(Producto);
        }
        transaction.commit();
    }

    @Override
    public List<Producto> findAll(Long empresaId) {
        TypedQuery<Producto> query = entityManager.createQuery("SELECT p FROM Producto p where p.empresa.id=:empresaId",
                Producto.class);
        query.setParameter("empresaId", empresaId);
        return query.getResultList();
    }


public Long count() {
    TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(p) FROM Producto p", Long.class);
    return query.getSingleResult();
}

    public Optional<Producto> findByCodigo(String codigo) {
        try {
            TypedQuery<Producto> query = entityManager.createQuery("SELECT p FROM Producto p WHERE p.codigo = :codigo",
                    Producto.class);
            query.setParameter("codigo", codigo);
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Producto> findAll(String nome, Long empresaId) {
        TypedQuery<Producto> query = entityManager.createQuery(
                "SELECT p FROM Producto p WHERE p.nome LIKE :nome or p.codigo LIKE:nome or p.categoria.nome LIKE:nome AND p.empresa.id=:empresaId",
                Producto.class);
        query.setParameter("nome", "%" + nome + "%");
        query.setParameter("empresaId", empresaId);
        return query.getResultList();
    }

}
