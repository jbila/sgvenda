package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
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
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import com.meldev.dao.CategoriaDao;
import com.meldev.model.Categoria;
import com.meldev.model.Empresa;
import java.awt.Color;

public class FrmCategoria extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtSearch;
    private JTable table;
    private CategoriaDao categoriaDao;
    private JTextField txtDescricao;
    private JLabel lblInfo;
    private Empresa empresa = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmCategoria frame = new FrmCategoria();
                    frame.setVisible(true);
                   // frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FrmCategoria() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(-5, 17, 689, 376);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, -29, 679, 369);
        contentPane.add(panel);
        panel.setLayout(null);

        txtId = new JTextField();
        txtId.setEditable(false);
        txtId.setBounds(82, 63, 114, 19);
        panel.add(txtId);
        txtId.setColumns(10);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(9, 65, 70, 15);
        panel.add(lblId);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(5, 105, 70, 15);
        panel.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(90, 94, 393, 26);
        panel.add(txtNome);
        txtNome.setColumns(10);

        JButton btnSave = new JButton("Gravar");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        btnSave.setBounds(500, 94, 117, 25);
        panel.add(btnSave);

        JButton btnDelete = new JButton("Apagar");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delete();
                showCategoria();
            }
        });
        btnDelete.setBounds(500, 150, 117, 25);
        panel.add(btnDelete);

        txtSearch = new JTextField();
        txtSearch.setBounds(90, 172, 397, 32);
        panel.add(txtSearch);
        txtSearch.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setBounds(70, 216, 480, 141);
        panel.add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    Object id = table.getValueAt(selectedRow, 0);
                    Object nome = table.getValueAt(selectedRow, 1);
                    Object descricao = table.getValueAt(selectedRow, 2);
                    txtId.setText(id.toString());
                    txtNome.setText(nome.toString());
                    txtDescricao.setText(descricao.toString());
                }
            }
        });

        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Nome", "Descricao"}) {
            private static final long serialVersionUID = 1L;
            boolean[] columnEditables = new boolean[]{false, false, false};

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(15);
        table.getColumnModel().getColumn(2).setPreferredWidth(35);
        table.setFillsViewportHeight(true);
        scrollPane.setViewportView(table);

        JLabel lblNewLabel = new JLabel("Descricao");
        lblNewLabel.setBounds(1, 142, 70, 15);
        panel.add(lblNewLabel);

        txtDescricao = new JTextField();
        txtDescricao.setBounds(90, 132, 393, 28);
        panel.add(txtDescricao);
        txtDescricao.setColumns(10);

        JLabel lblPesquisar = new JLabel("Pesquisar");
        lblPesquisar.setBounds(5, 172, 86, 32);
        panel.add(lblPesquisar);

        JLabel lblRegistoDeCategoria = new JLabel("Registo de categoria");
        lblRegistoDeCategoria.setForeground(new Color(51, 102, 255));
        lblRegistoDeCategoria.setFont(new Font("Dialog", Font.BOLD, 18));
        lblRegistoDeCategoria.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegistoDeCategoria.setBounds(9, 28, 639, 32);
        panel.add(lblRegistoDeCategoria);

        categoriaDao = new CategoriaDao();
        empresa = FrmLogin.empresa;
        showCategoria();
    }

    private void save() {
        String ID = txtId.getText();
        String nome = txtNome.getText();
        Categoria categoria = new Categoria();
        categoria.setEmpresa(empresa);

        if (!nome.isEmpty()) {
            categoria.setNome(nome);
            categoria.setDescricao(txtDescricao.getText());
            if (ID.equals("")) {
                categoria.setId(null);
            } else {
                categoria.setId(Long.valueOf(ID));
            }

            Categoria cat = categoriaDao.save(categoria);
            JOptionPane.showMessageDialog(rootPane, cat.getNome() + " Salvo");
            showCategoria();
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "Introduza o Nome");
        }

    }

    private void delete() {
        if (!txtId.getText().isEmpty()) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Eliminar este Registo?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                categoriaDao.deleteById(Long.valueOf(txtId.getText()));
                showCategoria();
                clear();
            } else {
                lblInfo.setText("Selecione a linha na tabela");
            }

        }

    }

    private void clear() {
        txtId.setText("");
        txtNome.setText("");
        txtDescricao.setText("");

    }

    private void showCategoria() {
        List<Categoria> categorias = categoriaDao.findAll(empresa.getId());
        showInfo(categorias);
    }

    private void showInfo(List<Categoria> categorias) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] row = new Object[3];
   model.setRowCount(0);// empty the table
        for (int i = 0; i < categorias.size(); i++) {
            row[0] = categorias.get(i).getId();
            row[1] = categorias.get(i).getNome();
            row[2] = categorias.get(i).getDescricao();
            model.addRow(row);
        }

    }
}