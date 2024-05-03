package com.meldev.reports;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.SessionFactoryImpl;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

public class Reports {

    private final Connection connection;

    public Reports() throws SQLException {
        this.connection = createConnection();
    }

    private Connection createConnection() throws SQLException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_DATA");
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
        ConnectionProvider connectionProvider = ((SessionFactoryImpl) sessionFactory).getServiceRegistry()
                .getService(ConnectionProvider.class);
        return connectionProvider.getConnection();
    }

    /*------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    private String getOs() {
        String osName = System.getProperty("os.name");
        String filePath;
        if (osName.startsWith("Windows")) {
            filePath = "C:\\meldev\\";
        } else if (osName.startsWith("Linux")) {
            filePath = "/home/jbila/meldev/";
        } else {
            // Default path for other/unknown OS
            filePath = "/default/path";
        }

        return filePath;
    }

    /*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public void generateReport() {
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(getOs() + "tecnico.jasper", null, connection);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {

            JOptionPane.showInternalMessageDialog(null, "Erro de " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    /**
     * ----------------------------------------------------------------------------cliente---------------------------------------------------------------------
     */
    public void generateReportCliente() {
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(getOs() + "cliente.jasper", null, connection);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {

            JOptionPane.showInternalMessageDialog(null, "Erro de " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    /**
     * -----------------------------------------------------------cliente-----------------------------------------------------------------------------------------
     */
    public void generateReportFornecedor() {
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(getOs() + "fornecedor.jasper", null, connection);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {

            JOptionPane.showInternalMessageDialog(null, "Erro de " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    /**
     * --------------------------------------cliente------------------------------------------------------------------------------------------------------------
     */
    public void generateReportCategoria() {
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(getOs() + "categoria.jasper", null, connection);
          //  JasperViewer.viewReport(jasperPrint, false);
            
            JFrame frame = new JFrame("Relatorio");
            frame.setSize(900, 850);
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setResizable(false);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a JRViewer and set the JasperPrint as the report data
            JRViewer viewer = new JRViewer(jasperPrint);
            frame.add(viewer);

            frame.setVisible(true);

        } catch (JRException e) {

            JOptionPane.showInternalMessageDialog(null, "Erro de " + e.getMessage());
        } finally {
            closeConnection();
        }
    }


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public void generateReportFrame() {
      

        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(getOs() + "tecnico", null, connection);

            // Create a JFrame to display the report
            JFrame frame = new JFrame("Relatorio");
            frame.setSize(900, 850);
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setResizable(false);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a JRViewer and set the JasperPrint as the report data
            JRViewer viewer = new JRViewer(jasperPrint);
            frame.add(viewer);

            frame.setVisible(true);
        } catch (HeadlessException | JRException e) {
            JOptionPane.showInternalMessageDialog(null, "Erro de " + e.getMessage());
        } finally {
            closeConnection();
        }

    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public void relatorioVenda(String data_inicio, String data_final) {
        JFrame frame = new JFrame("Relatorio");
        try {

            HashMap<String, Object> parametro = new HashMap<>();
            parametro.put("DATA_INICIO", data_inicio);
            parametro.put("DATA_FINAL", data_final);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getOs() + "pedidos.jasper", parametro, connection);

            // Create a JFrame to display the report
            frame.setSize(900, 850);
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setResizable(false);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a JRViewer and set the JasperPrint as the report data
            JRViewer viewer = new JRViewer(jasperPrint);
            frame.add(viewer);

            frame.setVisible(true);
        } catch (JRException e) {

            JOptionPane.showMessageDialog(null, "Erro de " + e.getMessage());
        } finally {
            closeConnection();
        }

    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public void expensesReport(String month, int year, Long empresaId) {
        JFrame frame = new JFrame("Relatorio");
        try {

            HashMap<String, Object> parametro = new HashMap<>();
            parametro.put("ANO", year);
            parametro.put("MES", month);
            parametro.put("EMPRESA", empresaId);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getOs() + "despesa.jasper", parametro, connection);

            // Create a JFrame to display the report
            frame.setSize(900, 850);
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setResizable(false);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a JRViewer and set the JasperPrint as the report data
            JRViewer viewer = new JRViewer(jasperPrint);
            frame.add(viewer);

            frame.setVisible(true);
        } catch (JRException e) {

            JOptionPane.showInternalMessageDialog(null, "Erro de " + e.getMessage());
        } finally {
            closeConnection();
        }

    }

    /*---------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public void incomeReportByYear(Integer year, Long empresaId) {
        JFrame frame = new JFrame("Relatorio");
        try {

            HashMap<String, Object> parametro = new HashMap<>();
            parametro.put("ANO", year);
            parametro.put("EMPRESA", empresaId);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getOs() + "income_by_year.jasper", parametro, connection);

            // Create a JFrame to display the report
            frame.setSize(900, 850);
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setResizable(false);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a JRViewer and set the JasperPrint as the report data
            JRViewer viewer = new JRViewer(jasperPrint);
            frame.add(viewer);

            frame.setVisible(true);
        } catch (JRException e) {

            JOptionPane.showInternalMessageDialog(null, "Erro de " + e.getMessage());
        } finally {
            closeConnection();
        }

    }

    /*-----------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public void incomeReportByMonthAndYear(String month, int year, Long empresaId) {
        JFrame frame = new JFrame("Relatorio");
        try {

            HashMap<String, Object> parametro = new HashMap<>();
            parametro.put("ANO", year);
            parametro.put("MES", month);
            parametro.put("EMPRESA", empresaId);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getOs() + "income_by_month_year.jasper", parametro, connection);

            // Create a JFrame to display the report
            frame.setSize(900, 850);
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setResizable(false);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a JRViewer and set the JasperPrint as the report data
            JRViewer viewer = new JRViewer(jasperPrint);
            frame.add(viewer);

            frame.setVisible(true);
        } catch (JRException e) {

            JOptionPane.showInternalMessageDialog(null, "Erro de " + e.getMessage());
        } finally {
            closeConnection();
        }

    }

    /*---------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public void relatorioStock() {
        JFrame frame = new JFrame("Relatorio");
        try {

            JasperPrint jasperPrint = JasperFillManager.fillReport(getOs() + "stock.jasper", null, connection);

            // Create a JFrame to display the report
            frame.setSize(900, 850);
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setResizable(false);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a JRViewer and set the JasperPrint as the report data
            JRViewer viewer = new JRViewer(jasperPrint);
            frame.add(viewer);

            frame.setVisible(true);
        } catch (JRException e) {

            JOptionPane.showMessageDialog(null, "Erro de " + e.getMessage());
        } finally {
            closeConnection();
        }

    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public void relatorioStockBaixo() {
        JFrame frame = new JFrame("Relatorio");
        try {

            JasperPrint jasperPrint = JasperFillManager.fillReport(getOs() + "stocklow.jasper", null, connection);

            // Create a JFrame to display the report
            frame.setSize(900, 850);
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setResizable(false);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a JRViewer and set the JasperPrint as the report data
            JRViewer viewer = new JRViewer(jasperPrint);
            frame.add(viewer);

            frame.setVisible(true);
        } catch (JRException e) {

            JOptionPane.showMessageDialog(null, "Erro de " + e.getMessage());
        } finally {
            closeConnection();
        }

    }

    /*---------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public void relatorioProducto() {
        JFrame frame = new JFrame("Relatorio");
        try {

            JasperPrint jasperPrint = JasperFillManager.fillReport(getOs() + "producto.jasper", null, connection);

            // Create a JFrame to display the report
            frame.setSize(900, 850);
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setResizable(false);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a JRViewer and set the JasperPrint as the report data
            JRViewer viewer = new JRViewer(jasperPrint);
            frame.add(viewer);

            frame.setVisible(true);
        } catch (JRException e) {

            JOptionPane.showMessageDialog(null, "Erro de " + e.getMessage());
        } finally {
            closeConnection();
        }

    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public void incomeReport(String month, int year, Long empresaId) {

        try {
            HashMap<String, Object> parametro = new HashMap<>();
            parametro.put("ANO", year);
            parametro.put("MES", month);
            parametro.put("EMPRESA", empresaId);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getOs() + "receita.jasper", parametro, connection);
            // Create a JFrame to display the report
            JFrame frame = new JFrame("Relatorio");
            frame.setSize(900, 850);
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create a JRViewer and set the JasperPrint as the report data
            JRViewer viewer = new JRViewer(jasperPrint);
            frame.add(viewer);

            frame.setVisible(true);
        } catch (HeadlessException | JRException e) {
            JOptionPane.showInternalMessageDialog(null, "Erro de " + e.getMessage());
        } finally {
            closeConnection();
        }

    }

    /*-----------------------------------------------------------------------------------------------------------------------------------------------------------*/
    private void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                JOptionPane.showInternalMessageDialog(null, "Erro clossing the connection " + e.getMessage());
            }
        }
    }
}
