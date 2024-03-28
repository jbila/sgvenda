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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.meldev.dao.ContaDao;
import com.meldev.model.Conta;
import com.meldev.model.Empresa;

public class FrmConta extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtSearch;
	private JTable table;
	private ContaDao contaDao;
	private JTextField txtDescricao;
	private JTextField txtNumero;
	private JTextField txtSaldo;
	private Empresa empresa = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConta frame = new FrmConta();
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
	public FrmConta() {
		setBackground(UIManager.getColor("Desktop.background"));
		setTitle("CONTA");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 689, 370);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Desktop.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Desktop.background"));
		panel.setBounds(12, -25, 667, 340);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblTipoDeDespesa = new JLabel("conta");
		lblTipoDeDespesa.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoDeDespesa.setFont(new Font("Courier New", Font.BOLD, 14));
		lblTipoDeDespesa.setBounds(12, 0, 643, 38);
		panel.add(lblTipoDeDespesa);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(82, 63, 114, 19);
		panel.add(txtId);
		txtId.setColumns(10);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(12, 65, 70, 15);
		panel.add(lblId);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 105, 52, 15);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(82, 101, 130, 28);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JButton btnSave = new JButton("Gravar");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();

			}
		});
		btnSave.setBounds(500, 94, 117, 31);
		panel.add(btnSave);

		JButton btnDelete = new JButton("Apagar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
				clear();
				showconta();
			}
		});
		btnDelete.setBounds(500, 152, 117, 28);
		panel.add(btnDelete);

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtSearch.getText().isEmpty()) {
					List<Conta> contas = contaDao.findAll(txtSearch.getText(),empresa.getId());
					showInfo(contas);

				} else
					showconta();
			}
		});
		txtSearch.setBounds(78, 175, 393, 32);
		panel.add(txtSearch);
		txtSearch.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(70, 216, 414, 88);
		panel.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				selection();
			}

		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome", "Descricao", "Numero", "Saldo" }) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		// table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.setFillsViewportHeight(true);

		JLabel lblTipoDeReceita = new JLabel("CONTAS");
		lblTipoDeReceita.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoDeReceita.setForeground(Color.BLUE);
		lblTipoDeReceita.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTipoDeReceita.setBounds(12, 23, 643, 38);
		panel.add(lblTipoDeReceita);

		JLabel lblDescricao = new JLabel("Descricao");
		lblDescricao.setBounds(221, 105, 70, 15);
		panel.add(lblDescricao);

		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		txtDescricao.setBounds(309, 99, 179, 28);
		panel.add(txtDescricao);

		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(82, 135, 130, 28);
		panel.add(txtNumero);

		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setBounds(10, 139, 63, 15);
		panel.add(lblNumero);

		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setBounds(221, 139, 70, 15);
		panel.add(lblSaldo);

		txtSaldo = new JTextField();
		txtSaldo.setColumns(10);
		txtSaldo.setBounds(309, 133, 179, 28);
		panel.add(txtSaldo);
		contaDao = new ContaDao();
		empresa = FrmLogin.empresa;
		showconta();
		
	}

	/**/
	private void save() {
		String nome = txtNome.getText().trim(); // Remove espaços em branco extras
		String ID = txtId.getText();
		Conta conta = new Conta();
		conta.setEmpresa(empresa);

		if (!nome.isEmpty()) {
			conta.setNome(nome);
			conta.setDescricao(txtNome.getText());
			conta.setNumero(txtNumero.getText());
			conta.setSaldo(Double.valueOf(txtSaldo.getText()));
			if (ID.equals("")) {
				conta.setId(null);
			} else {
				conta.setId(Long.valueOf(ID));
			}

			Conta cat = contaDao.save(conta);
			JOptionPane.showMessageDialog(rootPane, cat.getNome() + " Salvo");
			showconta();
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
				contaDao.deleteById(Long.valueOf(txtId.getText()));
				showconta();
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
		txtDescricao.setText("");
		txtNumero.setText("");
		txtSaldo.setText("");

	}

	//
	private void showconta() {
		if (empresa != null) {
			List<Conta> contas = contaDao.findAll(empresa.getId());
			showInfo(contas);
		}
	}

	private void showInfo(List<Conta> contas) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] row = new Object[5];
		model.setRowCount(0);// empty the table
		for (int i = 0; i < contas.size(); i++) {
			row[0] = contas.get(i).getId();
			row[1] = contas.get(i).getNome();
			row[2] = contas.get(i).getDescricao();
			row[3] = contas.get(i).getNumero();
			row[4] = contas.get(i).getSaldo();
			model.addRow(row);
		}

	}

	/*-----------------------------------------*/
	private void selection() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Object id = table.getValueAt(selectedRow, 0);
			Object nome = table.getValueAt(selectedRow, 1);
			Object descricao = table.getValueAt(selectedRow, 2);
			Object numero = table.getValueAt(selectedRow, 3);
			Object saldo = table.getValueAt(selectedRow, 4);
			txtId.setText(id.toString());
			txtNome.setText(nome.toString());
			txtDescricao.setText(descricao.toString());
			txtNumero.setText(numero.toString());
			txtSaldo.setText(saldo.toString());

		}

	}
}
