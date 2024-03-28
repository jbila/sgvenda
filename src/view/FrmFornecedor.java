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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.meldev.dao.FornecedorDao;
import com.meldev.model.Empresa;
import com.meldev.model.Fornecedor;

public class FrmFornecedor extends JFrame {

	private static final long serialVersionUID = 1L;

	private FornecedorDao fornecedorDao;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtWebSite;
	private JTextField txtNuit;
	private JTextField txtTelefone;
	private JTextField txtEndereco;
	private JTextField txtEmail;
	private JTable table;
	private JTextField txtSearch;
	private Empresa empresa=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmFornecedor frame = new FrmFornecedor();
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
	public FrmFornecedor() {
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
		panel.setBounds(12, -7, 814, 671);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 55, 757, 192);
		panel.add(panel_1);
		panel_1.setLayout(null);

		txtId = new JTextField();
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
		txtNome.setBounds(79, 50, 228, 34);
		panel_1.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("website");
		lblNewLabel_3.setBounds(325, 50, 76, 34);
		panel_1.add(lblNewLabel_3);

		txtWebSite = new JTextField();
		txtWebSite.setBounds(412, 51, 333, 34);
		panel_1.add(txtWebSite);
		txtWebSite.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Nuit");
		lblNewLabel_4.setBounds(0, 96, 76, 34);
		panel_1.add(lblNewLabel_4);

		txtNuit = new JTextField();
		txtNuit.setBounds(79, 97, 228, 34);
		panel_1.add(txtNuit);
		txtNuit.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Telefone");
		lblNewLabel_6.setBounds(325, 96, 75, 34);
		panel_1.add(lblNewLabel_6);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(412, 97, 333, 34);
		panel_1.add(txtTelefone);
		txtTelefone.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Endreco");
		lblNewLabel_7.setBounds(2, 142, 70, 34);
		panel_1.add(lblNewLabel_7);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(79, 142, 231, 34);
		panel_1.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(325, 142, 52, 34);
		panel_1.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(412, 143, 333, 34);
		panel_1.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(2, 8, 14, 15);
		panel_1.add(lblId);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBorder(new TitledBorder(null, "Accoes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 259, 754, 48);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(5, 16, 117, 25);
		panel_2.add(btnSave);

		JButton btnDelete = new JButton("Apagar");
		btnDelete.setBounds(632, 14, 117, 25);
		panel_2.add(btnDelete);
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
		scrollPane.setBounds(12, 375, 771, 281);
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
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome", "Codigo", "Nuit", "Telefone", "Email", "Endereco", "Website", }) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		txtSearch = new JTextField();
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setToolTipText("");
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					if (!txtSearch.getText().isEmpty()) {
						List<Fornecedor> fornecedors = fornecedorDao.findAll(txtSearch.getText(),empresa.getId());
						showInfo(fornecedors);
					} else
						showfornecedor();
				} catch (Exception ex) {

				}

			}
		});
		txtSearch.setBounds(18, 320, 611, 34);
		txtSearch.setColumns(10);
		panel.add(txtSearch);

		JLabel lblNewLabel = new JLabel("Pesquisar");
		lblNewLabel.setBounds(641, 320, 123, 32);
		panel.add(lblNewLabel);

		JLabel lblTipoDeReceita = new JLabel("Registo de Forncedor");
		lblTipoDeReceita.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoDeReceita.setForeground(Color.BLUE);
		lblTipoDeReceita.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTipoDeReceita.setBounds(10, 12, 752, 38);
		panel.add(lblTipoDeReceita);
		/*----------------*/
		fornecedorDao = new FornecedorDao();
		empresa=FrmLogin.empresa;

		showfornecedor();
	}


	/*------------------------------------------------*/
	private void save() {
		try {
			Fornecedor fornecedor = new Fornecedor();
			String ID = txtId.getText();
			String nome = txtNome.getText();
			fornecedor.setEmpresa(empresa);
			if (!nome.isEmpty()) {

				fornecedor.setCodigo(txtCodigo.getText());
				fornecedor.setNome(txtNome.getText());
				fornecedor.setEmail(txtEmail.getText());
				fornecedor.setEndereco(txtEndereco.getText());
				fornecedor.setTelefone(txtTelefone.getText());
				fornecedor.setNuit(txtNuit.getText());
				fornecedor.setWebsite(txtWebSite.getText());

				if (ID.equals("")) {
					fornecedor.setId(null);
				} else {
					fornecedor.setId(Long.valueOf(ID));
				}

				Fornecedor c = fornecedorDao.save(fornecedor);
				showfornecedor();
				clear();
				JOptionPane.showMessageDialog(rootPane, c.getNome() + " Registado com exito");

			} else {
				JOptionPane.showMessageDialog(null, "Introduza o Nome");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro, o campo codigo nao pode ser duplicado:");
		}

	}

	//
	private void showfornecedor() {
		List<Fornecedor> fornecedors = fornecedorDao.findAll(empresa.getId());
		showInfo(fornecedors);
	}

	private void showInfo(List<Fornecedor> fornecedors) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] row = new Object[8];
		model.setRowCount(0);// empty the table
		for (int i = 0; i < fornecedors.size(); i++) {
			row[0] = fornecedors.get(i).getId();
			row[1] = fornecedors.get(i).getNome();
			row[2] = fornecedors.get(i).getCodigo();
			row[3] = fornecedors.get(i).getNuit();
			row[4] = fornecedors.get(i).getTelefone();
			row[5] = fornecedors.get(i).getEmail();
			row[6] = fornecedors.get(i).getEndereco();
			row[7] = fornecedors.get(i).getWebsite();

			model.addRow(row);
		}

	}

	/*-------------------------------------*/
	private void selection() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Object id = table.getValueAt(selectedRow, 0);
			Object nome = table.getValueAt(selectedRow, 1);
			Object codigo = table.getValueAt(selectedRow, 2);
			Object nuit = table.getValueAt(selectedRow, 3);
			Object telefone = table.getValueAt(selectedRow, 4);
			Object email = table.getValueAt(selectedRow, 5);
			Object endereco = table.getValueAt(selectedRow, 6);
			Object webSite = table.getValueAt(selectedRow, 7);

			txtId.setText(id.toString());
			txtNome.setText(nome.toString());
			txtCodigo.setText(codigo.toString());
			txtNuit.setText(nuit.toString());
			txtTelefone.setText(telefone.toString());
			txtEmail.setText(email.toString());
			txtEndereco.setText(endereco.toString());
			txtWebSite.setText(webSite.toString());

		}

	}

	private void clear() {
		txtId.setText("");
		txtCodigo.setText("");
		txtNome.setText("");
		txtWebSite.setText("");
		txtNuit.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");
		txtEndereco.setText("");

	}

	private void delete() {
		try {
		if (!txtId.getText().isEmpty()) {
			int confirmation = JOptionPane.showConfirmDialog(this, "Eliminar o Registo ?",
					"Tem Certeza", JOptionPane.YES_NO_OPTION);
			if (confirmation == JOptionPane.YES_OPTION) {
				fornecedorDao.deleteById(Long.valueOf(txtId.getText()));
				showfornecedor();
				clear();
			} else {
				JOptionPane.showMessageDialog(table, "Selecione a linha na tabela");
			}

		}
		}
		catch (Exception e) {
			JOptionPane.showInternalConfirmDialog(table, "Selecione a linha na tabela");

		}

	}
}
