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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.meldev.dao.TipoDespesaDao;
import com.meldev.model.Empresa;
import com.meldev.model.TipoDespesa;

public class FrmTipoDespesa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtSearch;
	private JTable table;
	private TipoDespesaDao tipoDespesaDao;
	private Empresa empresa=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmTipoDespesa frame = new FrmTipoDespesa();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmTipoDespesa() {
		setTitle("TIPO Receita");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 708, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 667, 464);
		contentPane.add(panel);
		panel.setLayout(null);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(82, 63, 114, 19);
		panel.add(txtId);
		txtId.setColumns(10);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(12, 65, 70, 15);
		panel.add(lblId);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 105, 70, 15);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(78, 94, 393, 38);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JButton btnSave = new JButton("Gravar");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
				clear();
				showTipoDespesa();
			}
		});
		btnSave.setBounds(500, 44, 117, 25);
		panel.add(btnSave);

		JButton btnDelete = new JButton("Apagar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setBounds(500, 100, 117, 25);
		panel.add(btnDelete);

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtSearch.getText().isEmpty()) {
					List<TipoDespesa> tipoDespesas = tipoDespesaDao.findAll(txtSearch.getText(),empresa.getId());
					showInfo(tipoDespesas);

				} else {
					showTipoDespesa();
				}
			}
		});
		txtSearch.setBounds(78, 152, 393, 32);
		panel.add(txtSearch);
		txtSearch.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPesquisar.setBounds(500, 155, 117, 25);
		panel.add(btnPesquisar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(70, 216, 480, 236);
		panel.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selection();
			}
		});
		
		
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				 "ID", "Nome"
			}
		) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.setFillsViewportHeight(true);
		
		JLabel lblTipoDeDespesa = new JLabel("Tipo de Despesa");
		lblTipoDeDespesa.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoDeDespesa.setForeground(Color.BLUE);
		lblTipoDeDespesa.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTipoDeDespesa.setBounds(12, 0, 643, 38);
		panel.add(lblTipoDeDespesa);
		/*-----*/
		tipoDespesaDao = new TipoDespesaDao();
		empresa=FrmLogin.empresa;
		showTipoDespesa();
	}

	/*----------------------------*/
	private void save() {
		String nome = txtNome.getText().trim(); // Remove espaços em branco extras
		String ID = txtId.getText();
		TipoDespesa tipoDespesa = new TipoDespesa();
		tipoDespesa.setEmpresa(empresa);

		if (!nome.isEmpty()) {
			tipoDespesa.setNome(nome);
			if (ID.equals("")) {
				tipoDespesa.setId(null);
			} else {
				tipoDespesa.setId(Long.valueOf(ID));
			}

			TipoDespesa cat = tipoDespesaDao.save(tipoDespesa);
			JOptionPane.showMessageDialog(rootPane, cat.getNome() + " Saved");
			showTipoDespesa();
			clear();
		} else {
			JOptionPane.showMessageDialog(null, "Introduza o Nome");
		}

	}

	private void delete() {
		if (!txtId.getText().isEmpty()) {
			int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?",
					"Confirm Deletion", JOptionPane.YES_NO_OPTION);
			if (confirmation == JOptionPane.YES_OPTION) {
				tipoDespesaDao.deleteById(Long.valueOf(txtId.getText()));
				showTipoDespesa();
				clear();
			} else {

				JOptionPane.showMessageDialog(table, "Selecione a linha na tabela");
			}

		}

	}

	/*-------------------------------------------------------------------------*/
	private void clear() {
		txtId.setText("");
		txtNome.setText("");
		txtSearch.setText("");

	}

//
	private void showTipoDespesa() {
		List<TipoDespesa> tipoDespesas = tipoDespesaDao.findAll(empresa.getId());
		showInfo(tipoDespesas);
	}

	private void showInfo(List<TipoDespesa> tipoDespesas) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] row = new Object[3];
		model.setRowCount(0);// empty the table
		for (int i = 0; i < tipoDespesas.size(); i++) {
			row[0] = tipoDespesas.get(i).getId();
			row[1] = tipoDespesas.get(i).getNome();
			model.addRow(row);
		}

	}

	/*-----------------------------------------*/
	private void selection() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Object id = table.getValueAt(selectedRow, 0);
			Object nome = table.getValueAt(selectedRow, 1);
			txtId.setText(id.toString());
			txtNome.setText(nome.toString());
		}

	}
}
