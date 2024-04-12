package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.meldev.dao.FornecedorDao;
import com.meldev.dao.ProductoDao;
import dao.StockDao;
import com.meldev.model.Empresa;
import com.meldev.model.Fornecedor;
import model.Producto;
import com.meldev.model.Stock;
import com.meldev.reports.Reports;
import java.awt.HeadlessException;

import javax.swing.ImageIcon;

public class FrmStock extends JFrame {

    private static final long serialVersionUID = 1L;

    private StockDao stockDao;
    private final ProductoDao productoDao;
    private final FornecedorDao fornecedorDao;
    private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtCodigo;
    private JTextField txtMax;
    private JTable table;
    private JTextField txtSearch;
    private JTextField txtArtigo;
    private JTextField txtMin;
    private JTextField txtDescricao;
    private JTextField txtValidade;
    private JTextField txtCompra;
    private JTextField txtVenda;
    private JTextField txtQty;
    private JComboBox<Fornecedor> cboFornecedor;
    private Optional<Producto> productoOptional;
    private String codigoProducto = "";
    private JTextField txtCategoria;
    private JTextField txtUnidadeMedida;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmStock frame = new FrmStock();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public FrmStock() {
        setBackground(new Color(255, 255, 255));
        setResizable(false);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1180, 713);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(12, -24, 1146, 683);
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(22, 75, 1122, 180);
        panel.add(panel_1);
        panel_1.setLayout(null);

        txtId = new JTextField();
        txtId.setFont(new Font("Dialog", Font.BOLD, 14));
        txtId.setForeground(new Color(153, 0, 0));
        txtId.setBounds(79, 4, 115, 34);
        panel_1.add(txtId);
        txtId.setEditable(false);
        txtId.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Codigo");
        lblNewLabel_1.setBounds(212, 3, 70, 34);
        panel_1.add(lblNewLabel_1);

        txtCodigo = new JTextField();

        txtCodigo.setBounds(262, 4, 483, 34);
        panel_1.add(txtCodigo);
        txtCodigo.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("MAX");
        lblNewLabel_2.setBounds(25, 143, 45, 34);
        panel_1.add(lblNewLabel_2);

        txtMax = new JTextField();
        txtMax.setBounds(77, 143, 115, 34);
        panel_1.add(txtMax);
        txtMax.setColumns(10);

        JLabel lblId = new JLabel("ID");
        lblId.setBounds(2, 8, 14, 15);
        panel_1.add(lblId);

        JLabel lblNewLabel_1_1 = new JLabel("Artigo");
        lblNewLabel_1_1.setBounds(7, 50, 70, 34);
        panel_1.add(lblNewLabel_1_1);

        txtArtigo = new JTextField();
        txtArtigo.setColumns(10);
        txtArtigo.setBounds(79, 51, 324, 34);
        panel_1.add(txtArtigo);

        JLabel lblNewLabel_2_1 = new JLabel("MIN");
        lblNewLabel_2_1.setBounds(242, 143, 39, 34);
        panel_1.add(lblNewLabel_2_1);

        txtMin = new JTextField();
        txtMin.setColumns(10);
        txtMin.setBounds(288, 143, 115, 34);
        panel_1.add(txtMin);

        JLabel lblNewLabel_2_1_1 = new JLabel("Descricao");
        lblNewLabel_2_1_1.setBounds(424, 50, 70, 34);
        panel_1.add(lblNewLabel_2_1_1);

        txtDescricao = new JTextField();
        txtDescricao.setColumns(10);
        txtDescricao.setBounds(512, 50, 232, 34);
        panel_1.add(txtDescricao);

        JLabel lblNewLabel_1_1_1 = new JLabel("Fornecedor");
        lblNewLabel_1_1_1.setBounds(761, 93, 82, 34);
        panel_1.add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_1_1_2 = new JLabel("Validade");
        lblNewLabel_1_1_2.setBounds(2, 97, 70, 34);
        panel_1.add(lblNewLabel_1_1_2);

        txtValidade = new JTextField();
        txtValidade.setColumns(10);
        txtValidade.setBounds(79, 98, 115, 34);
        panel_1.add(txtValidade);

        JLabel lblNewLabel_1_1_2_1 = new JLabel("P.compra");
        lblNewLabel_1_1_2_1.setBounds(202, 96, 70, 34);
        panel_1.add(lblNewLabel_1_1_2_1);

        txtCompra = new JTextField();
        txtCompra.setColumns(10);
        txtCompra.setBounds(288, 97, 115, 34);
        panel_1.add(txtCompra);

        JLabel lblNewLabel_1_1_2_2 = new JLabel("P.venda");
        lblNewLabel_1_1_2_2.setBounds(427, 97, 70, 34);
        panel_1.add(lblNewLabel_1_1_2_2);

        txtVenda = new JTextField();
        txtVenda.setColumns(10);
        txtVenda.setBounds(512, 98, 70, 34);
        panel_1.add(txtVenda);

        txtQty = new JTextField();
        txtQty.setColumns(10);
        txtQty.setBounds(663, 96, 82, 34);
        panel_1.add(txtQty);

        JLabel lblQty = new JLabel("Qty");
        lblQty.setBounds(600, 106, 45, 15);
        panel_1.add(lblQty);

        cboFornecedor = new JComboBox<>();
        cboFornecedor.setBounds(878, 96, 232, 29);
        panel_1.add(cboFornecedor);

        txtCategoria = new JTextField();
        txtCategoria.setColumns(10);
        txtCategoria.setBounds(878, 4, 232, 34);
        panel_1.add(txtCategoria);

        JLabel lblNewLabel_2_1_1_1 = new JLabel("Categoria");
        lblNewLabel_2_1_1_1.setBounds(748, 4, 70, 34);
        panel_1.add(lblNewLabel_2_1_1_1);

        JLabel lblNewLabel_2_1_1_2 = new JLabel("UM");
        lblNewLabel_2_1_1_2.setBounds(761, 50, 70, 34);
        panel_1.add(lblNewLabel_2_1_1_2);

        txtUnidadeMedida = new JTextField();
        txtUnidadeMedida.setColumns(10);
        txtUnidadeMedida.setBounds(878, 50, 232, 34);
        panel_1.add(txtUnidadeMedida);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBorder(new TitledBorder(null, "Accoes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setBounds(12, 264, 1122, 62);
        panel.add(panel_2);
        panel_2.setLayout(null);

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(93, 25, 117, 25);
        panel_2.add(btnSave);

        JButton btnDelete = new JButton("Apagar");
        btnDelete.setBounds(633, 25, 117, 25);
        panel_2.add(btnDelete);

        JButton btnPrint = new JButton("Relatorio");
        btnPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // relatorio de stock
                try {
                    Reports r = new Reports();
                    r.relatorioStock();
                } catch (Exception ex) {

                }

            }
        });
        btnPrint.setIcon(new ImageIcon(FrmStock.class.getResource("/resources/icons8-print-24.png")));
        btnPrint.setBounds(787, 25, 157, 25);
        panel_2.add(btnPrint);

        JButton btnSLowStock = new JButton("stock baixo");
        btnSLowStock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // relatorio de stock
                try {
                    Reports r = new Reports();
                    r.relatorioStockBaixo();
                } catch (Exception ex) {

                }

            }
        });
        btnSLowStock.setIcon(new ImageIcon(FrmStock.class.getResource("/resources/icons8-print-24(1).png")));
        btnSLowStock.setBounds(953, 25, 157, 25);
        panel_2.add(btnSLowStock);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delete();

            }
        });
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save();

            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 362, 1122, 298);
        panel.add(scrollPane);

        table = new JTable();
        table.setForeground(new Color(0, 0, 0));
        table.setBackground(new Color(255, 255, 255));
        table.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selection();
            }
        });
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Codigo", "Artigo", "Descricao",
            "Cap", "UM", "Categoria", "P.C", "P.V", "Qty", "Validade", "Fornecedor", "Max", "Min"}) {
            private static final long serialVersionUID = 1L;
            boolean[] columnEditables = new boolean[]{false, false, false, false, false, false, false, false, false,
                false, false, false, false, false};

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(10);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(5);
        table.getColumnModel().getColumn(5).setPreferredWidth(5);
        table.getColumnModel().getColumn(7).setPreferredWidth(10);
        table.getColumnModel().getColumn(8).setPreferredWidth(10);

        table.getColumnModel().getColumn(9).setPreferredWidth(3);
        table.getColumnModel().getColumn(12).setPreferredWidth(3);
        table.getColumnModel().getColumn(13).setPreferredWidth(3);

        txtSearch = new JTextField();
        txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
        txtSearch.setToolTipText("");
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    if (!txtSearch.getText().isEmpty()) {
                        List<Stock> stocks = stockDao.findAll(txtSearch.getText(), FrmLogin.empresa.getId());
                        showInfo(stocks);
                    } else {
                        showstock();
                    }
                } catch (Exception ex) {

                }

            }
        });
        txtSearch.setBounds(12, 324, 981, 34);
        txtSearch.setColumns(10);
        panel.add(txtSearch);

        JLabel lblNewLabel = new JLabel("Pesquisar");
        lblNewLabel.setBounds(994, 324, 123, 32);
        panel.add(lblNewLabel);

        JLabel lblTipoDeReceita = new JLabel("STOCK CENTRAL");
        lblTipoDeReceita.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipoDeReceita.setForeground(Color.BLUE);
        lblTipoDeReceita.setFont(new Font("Dialog", Font.BOLD, 16));
        lblTipoDeReceita.setBounds(12, 25, 1132, 38);
        panel.add(lblTipoDeReceita);
        /*----------------*/
        stockDao = new StockDao();
        productoDao = new ProductoDao();
        fornecedorDao = new FornecedorDao();
        showstock();
        fillFornecedor();

    }

    /*------------------------------------------------*/
    private void save() {

        try {
            Stock stock = new Stock();
            String ID = txtId.getText();
            String nome = txtVenda.getText();

            if (!nome.isEmpty()) {
                if (ID.equals("")) {
                    stock.setId(null);
                    stock.setProducto(productoOptional.get());

                } else {
                    stock.setId(Long.valueOf(ID));
                    Optional<Producto> producto = productoDao.findByCodigo(codigoProducto);
                    stock.setProducto(producto.get());
                    // System.out.println(producto.get());

                }

                stock.setPrecoCompra(Double.parseDouble(txtCompra.getText()));
                stock.setPrecoVenda(Double.parseDouble(txtVenda.getText()));
                stock.setQty(Integer.parseInt(txtQty.getText()));
                Fornecedor fornecedor = (Fornecedor) cboFornecedor.getSelectedItem();
                stock.setFornecedor(fornecedor);
                stock.setMax(Integer.parseInt(txtMax.getText()));
                stock.setMin(Integer.parseInt(txtMin.getText()));
                stock.setValidade(txtValidade.getText());
                stock.setEmpresa(FrmLogin.empresa);
                Stock c = stockDao.save(stock);
                showstock();
                clear();
                JOptionPane.showMessageDialog(rootPane, c + " Stock actualizado  com sucesso");

            } else {
                JOptionPane.showMessageDialog(null, "Introduza as Informacoes correctas");
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error :" + e);
            // e.printStackTrace();
        }

    }

    //
    private void showstock() {

        if (FrmLogin.empresa.getId() != 0) {
            List<Stock> stocks = stockDao.findAll(FrmLogin.empresa.getId());

            showInfo(stocks);
        }

    }

    private void showInfo(List<Stock> stocks) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] row = new Object[14];
        model.setRowCount(0);// empty the table
        for (int i = 0; i < stocks.size(); i++) {
            row[0] = stocks.get(i).getId();
            row[1] = stocks.get(i).getProducto().getCodigo();
            row[2] = stocks.get(i).getProducto().getNome();
            row[3] = stocks.get(i).getProducto().getDescricao();
            row[4] = stocks.get(i).getProducto().getMedida();
            row[5] = stocks.get(i).getProducto().getUnidadeDeMedida().getNome();
            row[6] = stocks.get(i).getProducto().getCategoria().getNome();
            row[7] = stocks.get(i).getPrecoCompra();
            row[8] = stocks.get(i).getPrecoVenda();
            row[9] = stocks.get(i).getQty();
            row[10] = stocks.get(i).getValidade();
            row[11] = stocks.get(i).getFornecedor().getNome();
            row[12] = stocks.get(i).getMax();
            row[13] = stocks.get(i).getMin();
            model.addRow(row);
        }

    }

    /*-------------------------------------*/
    private void selection() {
        try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                Object id = table.getValueAt(selectedRow, 0);
                Object codigo = table.getValueAt(selectedRow, 1);
                Object producto = table.getValueAt(selectedRow, 2);
                Object descricao = table.getValueAt(selectedRow, 3);
                // Object capacidade = table.getValueAt(selectedRow, 4);
                Object um = table.getValueAt(selectedRow, 5);
                Object categoria = table.getValueAt(selectedRow, 6);
                Object precoCompra = table.getValueAt(selectedRow, 7);
                Object precoVenda = table.getValueAt(selectedRow, 8);
                Object qty = table.getValueAt(selectedRow, 9);
                Object validade = table.getValueAt(selectedRow, 10);
                Object fornecedor = table.getValueAt(selectedRow, 11);
                Object max = table.getValueAt(selectedRow, 12);
                Object min = table.getValueAt(selectedRow, 13);

                codigoProducto = codigo.toString();// inicializado para outros fins
                txtId.setText(id.toString());
                txtCodigo.setText(codigo.toString());
                txtArtigo.setText(producto.toString());
                txtDescricao.setText(descricao.toString());
                txtUnidadeMedida.setText(um.toString());
                txtCompra.setText(precoCompra.toString());
                txtVenda.setText(precoVenda.toString());
                txtQty.setText(qty.toString());
                txtValidade.setText(validade.toString());
                txtMax.setText(max.toString());
                txtMin.setText(min.toString());
                txtCategoria.setText(categoria.toString());
                // Check if fornecedor is not null before casting
                if (fornecedor != null && fornecedor instanceof Fornecedor) {
                    Fornecedor fornecedorObj = (Fornecedor) fornecedor;
                    cboFornecedor.removeAllItems();
                    cboFornecedor.setSelectedItem(fornecedorObj);
                }

            }
        } catch (NullPointerException ex) {

        }

    }

    private void clear() {
        txtId.setText("");
        txtCodigo.setText("");
        txtMax.setText("");
        txtMin.setText("");
        txtArtigo.setText("");
        txtDescricao.setText("");
        txtQty.setText("");
        txtUnidadeMedida.setText("");
        txtCategoria.setText("");

    }

    private void fillFornecedor() {
        if (FrmLogin.empresa != null) {
            List<Fornecedor> fornecedores = fornecedorDao.findAll(FrmLogin.empresa.getId());

            cboFornecedor.removeAllItems();
            for (Fornecedor fornecedor : fornecedores) {
                cboFornecedor.addItem(fornecedor);

            }
        }
    }

    private void delete() {
        if (!txtId.getText().isEmpty()) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                stockDao.deleteById(Long.valueOf(txtId.getText()));
                showstock();
                clear();
            } else {
                JOptionPane.showMessageDialog(table, "Selecione a linha na tabela");
            }

        }

    }
}
