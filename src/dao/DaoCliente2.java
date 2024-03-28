package com.meldev.dao;
/*
 * package com.meldev.dao;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import javax.persistence.EntityManager; import
 * javax.persistence.EntityManagerFactory; import javax.persistence.Persistence;
 * import javax.swing.JOptionPane; import org.hibernate.Session; import
 * org.hibernate.SessionFactory; import org.hibernate.Transaction;
 * 
 * import com.meldev.model.Cliente;
 * 
 * import javafx.scene.control.Alert; import
 * javafx.scene.control.Alert.AlertType;
 * 
 * public class DaoCliente2 { Alert alertErro = new Alert(AlertType.ERROR);
 * Alert alertInfo = new Alert(AlertType.INFORMATION); Transaction transaction =
 * null;
 * 
 * EntityManagerFactory emf = (EntityManagerFactory)
 * Persistence.createEntityManagerFactory("JPA_DATA1"); EntityManager
 * entityManager = ((javax.persistence.EntityManagerFactory)
 * emf).createEntityManager();
 * 
 * // Get the Hibernate Session from the EntityManager in JPA Session session =
 * entityManager.unwrap(org.hibernate.Session.class); SessionFactory factory
 * =session.getSessionFactory();
 * 
 * 
 * public DaoCliente2() {
 * 
 * }
 * 
 * public void save(Cliente cliente) {
 * 
 * try { transaction = session.beginTransaction(); session.save(cliente);
 * JOptionPane.showMessageDialog(null, "Data Saved"); transaction.commit(); }
 * 
 * catch (Exception e) { if (transaction!=null) transaction.rollback();
 * e.printStackTrace(); } finally { session.close(); }
 * 
 * 
 * 
 * }
 * 
 * // -------------------------------------------------------------------------
 * 
 * @SuppressWarnings("unused") private Cliente findById(final int id) { Cliente
 * c=new Cliente();
 * 
 * JOptionPane.showMessageDialog(null, "Data Saved");
 * 
 * return c; }
 * 
 * // --------------------------------------------------------------------------
 * public void delete(Cliente cliente) {
 * 
 * try { transaction = session.beginTransaction(); session.delete(cliente);
 * JOptionPane.showMessageDialog(null, "Data Saved"); transaction.commit(); }
 * 
 * catch (Exception e) { if (transaction!=null) transaction.rollback();
 * e.printStackTrace(); } finally { session.close(); }
 * 
 * 
 * }
 * 
 * //---------------------------------------------------------------------------
 * --- public void update(Cliente cliente) {
 * 
 * try { transaction = session.beginTransaction(); session.update(cliente);
 * JOptionPane.showMessageDialog(null, "Data DELETED"); transaction.commit(); }
 * 
 * catch (Exception e) { if (transaction!=null) transaction.rollback();
 * e.printStackTrace(); } finally { session.close(); }
 * 
 * }
 * 
 * //---------------------------------------------------------------------------
 * ----------------------------------------------------
 * 
 * @SuppressWarnings("unchecked") public List<Cliente> getAllCliente() {
 * List<Cliente> listCliente = new ArrayList<Cliente>(); try { transaction =
 * session.beginTransaction(); listCliente =
 * session.createQuery("FROM Cliente").list();
 * 
 * // transaction.commit(); }
 * 
 * catch (Exception e) { if (transaction!=null) transaction.rollback();
 * e.printStackTrace(); } finally { //session.close(); }
 * 
 * 
 * return listCliente; }
 * 
 * //---------------------------------------------------------------------------
 * ------------------------------------------ public Cliente
 * getClienteByNomeApelidoNumero(String nomeApelidoNumero) { Cliente c = new
 * Cliente(); return c; }
 * 
 * }
 */