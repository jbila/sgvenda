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

import com.meldev.dao.DepartamentoDao;
import com.meldev.model.Departamento;
import com.meldev.model.Empresa;

import javax.swing.UIManager;

public class FrmDepartamento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtSearch;
	private JTable table;
	private Empresa empresa=null;

	

	private DepartamentoDao departamentoDao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDepartamento frame = new FrmDepartamento();
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
	public FrmDepartamento() {
		setBackground(UIManager.getColor("desktop"));
		setTitle("Departamento");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 689, 423);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("desktop"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("desktop"));
		panel.setBounds(12, -15, 667, 355);
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
		txtNome.setBounds(92, 94, 379, 38);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JButton btnSave = new JButton("Gravar");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
				clear();
			}
		});
		btnSave.setBounds(500, 94, 117, 25);
		panel.add(btnSave);

		JButton btnDelete = new JButton("Apagar");

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setBounds(500, 150, 117, 25);
		panel.add(btnDelete);

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtSearch.getText().isEmpty()) {
					List<Departamento> departamentos = departamentoDao.findAll(txtSearch.getText(),empresa.getId());
					showInfo(departamentos);
				} else {

					showDepartamento();
				}
			}
		});
		txtSearch.setBounds(92, 152, 379, 32);
		panel.add(txtSearch);
		txtSearch.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(70, 216, 480, 88);
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
		lblPesquisar.setBounds(2, 160, 82, 15);
		panel.add(lblPesquisar);
		
		JLabel lblTipoDeReceita = new JLabel("Departamento");
		lblTipoDeReceita.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoDeReceita.setForeground(Color.BLUE);
		lblTipoDeReceita.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTipoDeReceita.setBounds(12, 12, 643, 38);
		panel.add(lblTipoDeReceita);
		departamentoDao = new DepartamentoDao();
		empresa=FrmLogin.empresa;
		showDepartamento();
	}

	/*-------------------------------------------------------------------------*/
	private void save() {
		Departamento departamento = new Departamento();
		departamento.setEmpresa(empresa);
		String ID = txtId.getText();
		departamento.setNome(txtNome.getText());
		departamento.setDescricao(txtNome.getText());
		String nome = txtNome.getText().trim(); // Remove espaços em branco extras

		if (!nome.isEmpty()) {
			departamento.setNome(nome);
			departamento.setDescricao(txtNome.getText()); // Use txtDescricao em vez de txtNome
			if (ID.equals("")) {
				departamento.setId(null);
			} else {
				departamento.setId(Long.valueOf(ID));
			}

			Departamento cat = departamentoDao.save(departamento);
			JOptionPane.showMessageDialog(rootPane, cat.getNome() + " Salvo");
			showDepartamento();
			clear();
		} else {
			JOptionPane.showMessageDialog(null, "Introduza o Nome");
		}

	}

	private void delete() {
		if (!txtId.getText().isEmpty()) {
			int confirmation = JOptionPane.showConfirmDialog(this, "Eliminar o Registo?",
					"Confirm Deletion", JOptionPane.YES_NO_OPTION);
			if (confirmation == JOptionPane.YES_OPTION) {
				departamentoDao.deleteById(Long.valueOf(txtId.getText()));
				showDepartamento();
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
	private void showDepartamento() {
		List<Departamento> departamentos = departamentoDao.findAll(empresa.getId());
		showInfo(departamentos);
	}

	private void showInfo(List<Departamento> departamentos) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] row = new Object[3];
		model.setRowCount(0);// empty the table
		for (int i = 0; i < departamentos.size(); i++) {
			row[0] = departamentos.get(i).getId();
			row[1] = departamentos.get(i).getNome();
			model.addRow(row);
		}

	}

	/*--------------*/
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
