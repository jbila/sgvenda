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

import com.meldev.dao.TipoReceitaDao;
import com.meldev.model.Empresa;
import com.meldev.model.TipoReceita;
import javax.swing.ListSelectionModel;

public class FrmTipoReceita extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtSearch;
	private JTable table;
	private TipoReceitaDao tipoReceitaDao;
	private Empresa empresa=null;

	/**
	 * FrmTipoReceita Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmTipoReceita frame = new FrmTipoReceita();
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
	public FrmTipoReceita() {
		setTitle("TIPO Receita");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 689, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 667, 480);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTipoDeReceita = new JLabel("Tipo de Receita");
		lblTipoDeReceita.setForeground(new Color(0, 0, 255));
		lblTipoDeReceita.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoDeReceita.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTipoDeReceita.setBounds(12, 10, 643, 38);
		panel.add(lblTipoDeReceita);

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
				showTipoReceita();
			}
		});
		btnSave.setBounds(500, 51, 117, 25);
		panel.add(btnSave);

		JButton btnDelete = new JButton("Apagar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
				showTipoReceita();
			}
		});
		btnDelete.setBounds(500, 100, 117, 25);
		panel.add(btnDelete);

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtSearch.getText().isEmpty()) {
					List<TipoReceita> tipoReceitas = tipoReceitaDao.findAll(txtSearch.getText(),empresa.getId());
					showInfo(tipoReceitas);
					

				} else {
					showTipoReceita();
				}
			}
		});
		txtSearch.setBounds(82, 142, 393, 32);
		panel.add(txtSearch);
		txtSearch.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(70, 186, 473, 256);
		panel.add(scrollPane);

		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
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
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.setFillsViewportHeight(true);
		
		JLabel lblPesquisar = new JLabel("Pesquisar");
		lblPesquisar.setBounds(500, 150, 101, 15);
		panel.add(lblPesquisar);
		/*-----------------*/
		tipoReceitaDao = new TipoReceitaDao();
		empresa=FrmLogin.empresa;
		showTipoReceita();
	}

	/**/
	/**/
	private void save() {
		String nome = txtNome.getText().trim(); // Remove espaços em branco extras
		String ID = txtId.getText();
		TipoReceita tipoReceita = new TipoReceita();
		tipoReceita.setEmpresa(empresa);

		if (!nome.isEmpty()) {
			tipoReceita.setNome(nome);
			if (ID.equals("")) {
				tipoReceita.setId(null);
			} else {
				tipoReceita.setId(Long.valueOf(ID));
			}

			TipoReceita cat = tipoReceitaDao.save(tipoReceita);
			JOptionPane.showMessageDialog(rootPane, cat.getNome() + " Saved");
			showTipoReceita();
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
				tipoReceitaDao.deleteById(Long.valueOf(txtId.getText()));
				showTipoReceita();
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
	private void showTipoReceita() {
		List<TipoReceita> tipoReceitas = tipoReceitaDao.findAll(empresa.getId());
		showInfo(tipoReceitas);
	}

	private void showInfo(List<TipoReceita> tipoReceitas) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] row = new Object[3];
		model.setRowCount(0);// empty the table
		for (int i = 0; i < tipoReceitas.size(); i++) {
			row[0] = tipoReceitas.get(i).getId();
			row[1] = tipoReceitas.get(i).getNome();
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
