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
import java.time.LocalDate;
import java.util.List;

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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.meldev.dao.CategoriaDao;
import com.meldev.dao.FornecedorDao;
import com.meldev.dao.ProductoDao;
import com.meldev.dao.StockDao;
import com.meldev.dao.UnidadeDeMedidaDao;
import com.meldev.model.Categoria;
import com.meldev.model.Empresa;
import com.meldev.model.Fornecedor;
import model.Producto;
import com.meldev.model.Stock;
import com.meldev.model.UnidadeDeMedida;
import com.meldev.reports.Reports;

import javax.swing.ImageIcon;

public class FrmProducto extends JFrame {

    private static final long serialVersionUID = 1L;
    private CategoriaDao categoriaDao;
    private UnidadeDeMedidaDao unidadeDeMedidaDao;
    private ProductoDao productoDao;
    private StockDao stockDao;
    private FornecedorDao fornecedorDao;
    private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtCodigo;
    private JTextField txtNome;
    private JComboBox<Categoria> cboCategoria;
    private JComboBox<UnidadeDeMedida> cboUnidadeDeMedica;
    private JTable table;
    private JTextField txtSearch;
    private JTextField txtDescricao;
    private JTextField txtCapacidade;
    private Empresa empresa = null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmProducto frame = new FrmProducto();
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
    public FrmProducto() {
        setBackground(UIManager.getColor("desktop"));
        setResizable(false);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 826, 713);
        contentPane = new JPanel();
        contentPane.setBackground(UIManager.getColor("desktop"));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(UIManager.getColor("desktop"));
        panel.setBounds(12, -7, 814, 683);
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(10, 55, 757, 194);
        panel.add(panel_1);
        panel_1.setLayout(null);

        txtId = new JTextField();
        txtId.setFont(new Font("Dialog", Font.BOLD, 14));
        txtId.setForeground(new Color(153, 0, 0));
        txtId.setBounds(79, 4, 228, 34);
        panel_1.add(txtId);
        txtId.setEditable(false);
        txtId.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Codigo");
        lblNewLabel_1.setBounds(325, 3, 70, 34);
        panel_1.add(lblNewLabel_1);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(412, 4, 333, 34);
        panel_1.add(txtCodigo);
        txtCodigo.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Nome");
        lblNewLabel_2.setBounds(2, 50, 70, 34);
        panel_1.add(lblNewLabel_2);

        txtNome = new JTextField();
        txtNome.setBounds(79, 50, 666, 34);
        panel_1.add(txtNome);
        txtNome.setColumns(10);

        JLabel lblId = new JLabel("ID");
        lblId.setBounds(2, 8, 14, 15);
        panel_1.add(lblId);

        JLabel lblNewLabel_9 = new JLabel("UM");
        lblNewLabel_9.setBounds(473, 147, 36, 24);
        panel_1.add(lblNewLabel_9);

        JLabel lblNewLabel_8 = new JLabel("Categoria");
        lblNewLabel_8.setBounds(2, 147, 70, 24);
        panel_1.add(lblNewLabel_8);
        cboCategoria = new JComboBox<>();
        cboCategoria.setBounds(79, 141, 236, 37);
        panel_1.add(cboCategoria);

        cboUnidadeDeMedica = new JComboBox<>();
        cboUnidadeDeMedica.setBounds(517, 141, 228, 37);
        panel_1.add(cboUnidadeDeMedica);

        JLabel lblNewLabel_1_1 = new JLabel("Descricao");
        lblNewLabel_1_1.setBounds(2, 95, 70, 34);
        panel_1.add(lblNewLabel_1_1);

        txtDescricao = new JTextField();
        txtDescricao.setColumns(10);
        txtDescricao.setBounds(79, 96, 666, 34);
        panel_1.add(txtDescricao);

        JLabel lblNewLabel_9_1 = new JLabel("Capacidade");
        lblNewLabel_9_1.setBounds(325, 147, 92, 24);
        panel_1.add(lblNewLabel_9_1);

        txtCapacidade = new JTextField();
        txtCapacidade.setBounds(412, 147, 56, 24);
        panel_1.add(txtCapacidade);
        txtCapacidade.setColumns(10);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBorder(new TitledBorder(null, "Accoes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setBounds(20, 251, 754, 51);
        panel.add(panel_2);
        panel_2.setLayout(null);

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(5, 16, 117, 25);
        panel_2.add(btnSave);
        JButton btnUpdate = new JButton("Editar");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update();
                btnSave.setEnabled(true);
                btnUpdate.setEnabled(false);

            }
        });
        btnUpdate.setBounds(249, 16, 117, 25);
        panel_2.add(btnUpdate);

        JButton btnDelete = new JButton("Apagar");
        btnDelete.setBounds(632, 14, 117, 25);
        panel_2.add(btnDelete);

        JButton btnPrint = new JButton("Relatorio");
        btnPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //relatorio de stock
                try {
                    Reports r = new Reports();
                    r.relatorioProducto();
                } catch (Exception ex) {

                }

            }
        });
        btnPrint.setIcon(new ImageIcon(FrmProducto.class.getResource("/resources/icons8-print-24(1).png")));
        btnPrint.setBounds(470, 14, 136, 25);
        panel_2.add(btnPrint);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delete();
                btnSave.setEnabled(true);
                btnUpdate.setEnabled(false);
                btnDelete.setEnabled(false);

            }
        });
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save();
                btnSave.setEnabled(true);
                btnUpdate.setEnabled(false);
                btnDelete.setEnabled(false);

            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 362, 771, 298);
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
                btnSave.setEnabled(false);
                btnUpdate.setEnabled(true);
                btnDelete.setEnabled(true);

            }
        });
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(new Object[][]{},
                new String[]{"ID", "Codigo", "Nome", "Descricao", "Categoria", "Capacidade", "UM"}) {
            private static final long serialVersionUID = 1L;
            boolean[] columnEditables = new boolean[]{false, false, false, false, false, false, false};

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(1).setPreferredWidth(10);
        table.getColumnModel().getColumn(5).setPreferredWidth(15);
        table.getColumnModel().getColumn(6).setPreferredWidth(10);

        txtSearch = new JTextField();
        txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
        txtSearch.setToolTipText("");
        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    if (!txtSearch.getText().isEmpty()) {
                        List<Producto> productos = productoDao.findAll(txtSearch.getText(), empresa.getId());
                        showInfo(productos);
                    } else {
                        showproducto();
                    }
                } catch (Exception ex) {

                }

            }
        });
        txtSearch.setBounds(18, 314, 611, 34);
        txtSearch.setColumns(10);
        panel.add(txtSearch);

        JLabel lblNewLabel = new JLabel("Pesquisar");
        lblNewLabel.setBounds(641, 314, 123, 32);
        panel.add(lblNewLabel);

        JLabel lblTipoDeReceita = new JLabel("Registo de Producto");
        lblTipoDeReceita.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipoDeReceita.setForeground(Color.BLUE);
        lblTipoDeReceita.setFont(new Font("Dialog", Font.BOLD, 18));
        lblTipoDeReceita.setBounds(10, 12, 752, 38);
        panel.add(lblTipoDeReceita);
        /*----------------*/
        productoDao = new ProductoDao();
        categoriaDao = new CategoriaDao();
        unidadeDeMedidaDao = new UnidadeDeMedidaDao();
        fornecedorDao = new FornecedorDao();
        stockDao = new StockDao();
        empresa = FrmLogin.empresa;
        fillCategoria();
        fillUm();
        showproducto();
        /*--------------------------*/
        btnSave.setEnabled(true);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
    }

    /*-------------------------------------------*/
    private void fillCategoria() {
        List<Categoria> categorias = categoriaDao.findAll(empresa.getId());
        cboCategoria.removeAllItems();
        for (Categoria categoria : categorias) {
            cboCategoria.addItem(categoria);
        }
    }

    private void fillUm() {
        List<UnidadeDeMedida> unidadeDeMedidas = unidadeDeMedidaDao.findAll(empresa.getId());
        cboUnidadeDeMedica.removeAllItems();
        for (UnidadeDeMedida unidadeDeMedida : unidadeDeMedidas) {
            cboUnidadeDeMedica.addItem(unidadeDeMedida);
        }
    }

    /*------------------------------------------------*/
    private void save() {
        try {
            Producto producto = new Producto();
            producto.setEmpresa(empresa);
            String ID = txtId.getText();
            String nome = txtNome.getText();
            if (!nome.isEmpty()) {

                Categoria categoria = (Categoria) cboCategoria.getSelectedItem();
                UnidadeDeMedida unidadeDeMedida = (UnidadeDeMedida) cboUnidadeDeMedica.getSelectedItem();
                producto.setCodigo(txtCodigo.getText());
                producto.setNome(txtNome.getText());
                producto.setDescricao(txtDescricao.getText());
                producto.setCategoria(categoria);
                producto.setUnidadeDeMedida(unidadeDeMedida);
                producto.setMedida(Double.valueOf(txtCapacidade.getText()));

                if (ID.equals("")) {
                    producto.setId(null);
                } else {
                    producto.setId(Long.valueOf(ID));
                }

                Producto c = productoDao.save(producto);
                saveStock(producto);
                showproducto();
                clear();
                JOptionPane.showMessageDialog(rootPane, c.getNome() + " Registado com exito");

            } else {
                JOptionPane.showMessageDialog(null, "Introduza o Nome");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro, o campo codigo nao pode ser duplicado:" + e);
            e.printStackTrace();
        }

    }

    //update
    private void update() {
        try {
            Producto producto = new Producto();
            producto.setEmpresa(empresa);
            String ID = txtId.getText();
            String nome = txtNome.getText();
            if (!nome.isEmpty()) {

                Categoria categoria = (Categoria) cboCategoria.getSelectedItem();
                UnidadeDeMedida unidadeDeMedida = (UnidadeDeMedida) cboUnidadeDeMedica.getSelectedItem();
                producto.setCodigo(txtCodigo.getText());
                producto.setNome(txtNome.getText());
                producto.setDescricao(txtDescricao.getText());
                producto.setCategoria(categoria);
                producto.setUnidadeDeMedida(unidadeDeMedida);
                producto.setMedida(Double.valueOf(txtCapacidade.getText()));

                if (ID.equals("")) {
                    producto.setId(null);
                } else {
                    producto.setId(Long.valueOf(ID));
                }

                Producto c = productoDao.save(producto);

                showproducto();
                clear();
                JOptionPane.showMessageDialog(rootPane, c.getNome() + " Editado  com exito");

            } else {
                JOptionPane.showMessageDialog(null, "Introduza o Nome");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro, o campo codigo nao pode ser duplicado:" + e);
            e.printStackTrace();
        }

    }

    private void saveStock(Producto producto) {
        Stock stock = new Stock();
        stock.setId(null);
        stock.setPrecoCompra(0);
        stock.setPrecoVenda(0);
        stock.setQty(0);
        Fornecedor fornecedor = fornecedorDao.findById(2L);
        stock.setFornecedor(fornecedor);
        stock.setMax(0);
        stock.setMin(0);
        stock.setValidade("" + LocalDate.now().plusDays(30L));
        stock.setProducto(producto);
        stock.setEmpresa(empresa);
        System.out.println("STOCK " + producto.toString());
        stockDao.save(stock);
    }

    //
    private void showproducto() {
        List<Producto> productos = productoDao.findAll(empresa.getId());
        showInfo(productos);
    }

    private void showInfo(List<Producto> productos) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] row = new Object[7];
        model.setRowCount(0);// empty the table
        for (int i = 0; i < productos.size(); i++) {
            row[0] = productos.get(i).getId();
            row[1] = productos.get(i).getCodigo();
            row[2] = productos.get(i).getNome();
            row[3] = productos.get(i).getDescricao();
            row[4] = productos.get(i).getCategoria().getNome();
            row[5] = productos.get(i).getMedida();
            row[6] = productos.get(i).getUnidadeDeMedida().getNome();

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
                Object nome = table.getValueAt(selectedRow, 2);
                Object descricao = table.getValueAt(selectedRow, 3);
                Object categoria = table.getValueAt(selectedRow, 4);
                Object capacidade = table.getValueAt(selectedRow, 5);
                Object um = table.getValueAt(selectedRow, 6);

                txtId.setText(id.toString());
                txtNome.setText(nome.toString());
                txtCodigo.setText(codigo.toString());
                txtDescricao.setText(descricao.toString());
                txtCapacidade.setText(capacidade.toString());
                if (categoria != null && categoria instanceof Categoria) {
                    Categoria categoriaObj = (Categoria) categoria;
                    cboCategoria.removeAllItems();
                    cboCategoria.setSelectedItem(categoriaObj);
                }
                if (um != null && um instanceof UnidadeDeMedida) {
                    UnidadeDeMedida unidadeMedida = (UnidadeDeMedida) um;
                    cboUnidadeDeMedica.removeAllItems();
                    cboUnidadeDeMedica.addItem(unidadeMedida);
                }
            }
        } catch (NullPointerException ex) {

        }

    }

    private void clear() {
        txtId.setText("");
        txtCodigo.setText("");
        txtNome.setText("");
        txtDescricao.setText("");
        txtCapacidade.setText("");

    }

    private void delete() {
        if (!txtId.getText().isEmpty()) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                productoDao.deleteById(Long.valueOf(txtId.getText()));
                showproducto();
                clear();
            } else {
                JOptionPane.showMessageDialog(table, "Selecione a linha na tabela");
            }

        }

    }
}
