package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.Role;
import com.meldev.utils.JpaUtil;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class RoleDao implements GenericDao<Role> {

    private final EntityManager entityManager;

    public RoleDao() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public Role save(Role Role) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        if (Role.getId() != null) {
            entityManager.merge(Role);
        } else {
            entityManager.persist(Role);
        }
        transaction.commit();
        return Role;
    }

    @Override
    public List<Role> saveAll(List<Role> Roles) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Roles.forEach(Role -> entityManager.persist(Role));
        transaction.commit();
        return Roles;
    }

    @Override
    public void deleteById(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Role Role = entityManager.find(Role.class, id);
        if (Role != null) {
            entityManager.remove(Role);
        }
        transaction.commit();
    }

    public List<Role> findAll() {
        TypedQuery<Role> query = entityManager.createQuery("SELECT c FROM Role c", Role.class);
        return query.getResultList();
    }

    public List<Role> findByName(String nome) {
        TypedQuery<Role> query = entityManager.createQuery("SELECT c FROM Role c WHERE c.nome = :nome",
                Role.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    @Override
    public List<Role> findAll(Long empresaId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Role> findAll(String nome, Long empresaId) {
        // TODO Auto-generated method stub
        return null;
    }

    public long count() {
        Query query = entityManager.createQuery("SELECT COUNT(r) FROM Role r");
        return (long) query.getSingleResult();
    }

     public Role findByPerfil(String nome) {
        try {
            TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.nome = :nome", Role.class);
            query.setParameter("nome", nome);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Retorna null se nenhum resultado for encontrado
        }
    }

}
