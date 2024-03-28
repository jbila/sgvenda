package com.meldev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.meldev.model.ItemsPedidos;
import com.meldev.utils.JpaUtil;

public class ItemsPedidosDao implements GenericDao<ItemsPedidos> {

	private final EntityManager entityManager;

	public ItemsPedidosDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Override
	public ItemsPedidos save(ItemsPedidos ItemsPedidos) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (ItemsPedidos.getId() != null)
			entityManager.merge(ItemsPedidos);
		else
			entityManager.persist(ItemsPedidos);
		transaction.commit();
		return ItemsPedidos;
	}

	@Override
	public List<ItemsPedidos> saveAll(List<ItemsPedidos> ItemsPedidoss) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		ItemsPedidoss.forEach(ItemsPedidos -> entityManager.persist(ItemsPedidos));
		transaction.commit();
		return ItemsPedidoss;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		ItemsPedidos ItemsPedidos = entityManager.find(ItemsPedidos.class, id);
		if (ItemsPedidos != null) {
			entityManager.remove(ItemsPedidos);
		}
		transaction.commit();
	}

	@Override
	public List<ItemsPedidos> findAll(Long empresaId) {
		TypedQuery<ItemsPedidos> query = entityManager.createQuery("SELECT c FROM ItemsPedidos c where c.empresa.id=:empresaId", ItemsPedidos.class);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<ItemsPedidos> findByIdPedido(Long idPedido) {
		TypedQuery<ItemsPedidos> query = entityManager
				.createQuery("SELECT c FROM ItemsPedidos c WHERE c.pedido.id = :id_pedido", ItemsPedidos.class);
		query.setParameter("id_pedido", idPedido);
		return query.getResultList();
	}

	@Override
	public List<ItemsPedidos> findAll(String nome,Long empresaId) {
		// TODO Auto-generated method stub
		return null;
	}

}
