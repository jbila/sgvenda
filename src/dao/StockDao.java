package dao;

import com.meldev.dao.GenericDao;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.meldev.model.ItemsPedidos;
import com.meldev.model.Stock;
import com.meldev.utils.JpaUtil;
import javax.transaction.Transactional;

public class StockDao implements GenericDao<Stock> {

	private final EntityManager entityManager;

	public StockDao() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	@Transactional
	public void updateStockQuantitiesT(List<ItemsPedidos> itemsList) {
		for (ItemsPedidos item : itemsList) {
			Long productId = item.getProducto().getId();
			int quantityChange = item.getQuantidade();

			// Fetch the Stock entity by product ID
			Stock stock = entityManager.find(Stock.class, productId);

			if (stock != null) {
				// Update the quantity
				int newQuantity = stock.getQty() + quantityChange;
				stock.setQty(newQuantity);

				// Merge the updated Stock entity back into the persistence context
				entityManager.merge(stock);
			}
		}
	}

	@Override
	public Stock save(Stock stock) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		if (stock.getId() != null)
			entityManager.merge(stock);
		else
			entityManager.persist(stock);

		transaction.commit();
		return stock;
	}

	@Override
	public List<Stock> saveAll(List<Stock> Stocks) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Stocks.forEach(Stock -> entityManager.persist(Stock));
		transaction.commit();
		return Stocks;
	}

	@Override
	public void deleteById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Stock Stock = entityManager.find(Stock.class, id);
		if (Stock != null) {
			entityManager.remove(Stock);
		}
		transaction.commit();
	}

	@Override
	public List<Stock> findAll(Long empresaId) {
		TypedQuery<Stock> query = entityManager.createQuery("SELECT c FROM Stock c where c.empresa.id=:empresaId", Stock.class);
	    query.setParameter("empresaId", empresaId);
		return query.getResultList();
	}

	public List<Stock> findByName(String nome) {
		TypedQuery<Stock> query = entityManager.createQuery(
				"SELECT s FROM Stock s WHERE s.producto.nome = :nome or s.producto.codigo=:nome", Stock.class);
		query.setParameter("nome", nome);
		return query.getResultList();
	}

	@Override
	public List<Stock> findAll(String nome,Long empresaId) {
		TypedQuery<Stock> query = entityManager.createQuery(
				"SELECT s FROM Stock s WHERE s.producto.nome like :nome or s.producto.codigo like :nome or producto.categoria.nome like :nome AND s.empresa.id=:empresaId",
				Stock.class);
		query.setParameter("nome", nome + "%");
	    query.setParameter("empresaId", empresaId);

		return query.getResultList();
	}

	public Stock searchStockByName(String nome) {
		TypedQuery<Stock> query;
		if (nome != null && !nome.isEmpty()) {
			query = entityManager.createQuery(
					"SELECT s FROM Stock s WHERE s.producto.nome = :nome or s.producto.codigo=:nome", Stock.class);
			query.setParameter("nome", nome);
		} else {
			query = entityManager.createQuery("SELECT s FROM Stock s WHERE s.producto.nome = :nome", Stock.class);
		}
		return query.getSingleResult();
	}

	public List<Stock> findByCategoria(String categoria) {
		TypedQuery<Stock> query = entityManager
				.createQuery("SELECT s FROM Stock s WHERE s.producto.categoria.nome = :categoria", Stock.class);
		query.setParameter("categoria", categoria);
		return query.getResultList();
	}

	public Optional<Stock> findByProductoCodigo(String codigo) {
		TypedQuery<Stock> query = entityManager.createQuery("SELECT s FROM Stock s WHERE s.producto.codigo = :codigo",
				Stock.class);
		query.setParameter("codigo", codigo);
		try {
			return Optional.ofNullable(query.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}
           public long count() {
		return entityManager.createQuery("SELECT COUNT(s) FROM Stock s", Long.class).getSingleResult();
	}
             public long getBaixoStock() {
		return entityManager.createQuery("SELECT COUNT( s) FROM Stock s WHERE s.qty < s.min ", Long.class).getSingleResult();
	}
              public long getSockExpirados() {
		return entityManager.createQuery("SELECT COUNT(s) FROM Stock s WHERE s.validade < CURRENT_DATE ", Long.class).getSingleResult();
	}
}
