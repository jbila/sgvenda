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
import java.util.ArrayList;
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

import com.meldev.dao.ClienteDao;
import com.meldev.dao.DistritoDao;
import com.meldev.dao.ProvinciaDao;
import com.meldev.model.Cliente;
import com.meldev.model.Distrito;
import com.meldev.model.Empresa;
import com.meldev.model.Provincia;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FrmCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private ProvinciaDao provinciaDao;
	private DistritoDao distritoDao;
	private ClienteDao clienteDao;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtSearch;
	private JTextField txtId;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtNuit;
	private JTextField txtTelefone;
	private JTextField txtEndereco;
	private JTextField txtEmail;
	private JComboBox<Provincia> cboProvincia;
	private JComboBox<Distrito> cboDistrito;
	private JComboBox<String> cboGenero;
	private Empresa empresa=null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCliente frame = new FrmCliente();
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
	public FrmCliente() {
		setBackground(UIManager.getColor("desktop"));
		setResizable(false);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1035, 713);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("desktop"));
		panel.setBounds(12, 0, 997, 683);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBorder(new TitledBorder(null, "Accoes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(18, 291, 955, 48);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JButton btnSave = new JButton("Gravar");
		btnSave.setBounds(5, 16, 117, 25);
		panel_2.add(btnSave);

		JButton btnDelete = new JButton("Apagar");
		btnDelete.setBounds(801, 16, 117, 25);
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
		scrollPane.setBounds(12, 384, 961, 281);
		panel.add(scrollPane);

		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(new Color(255, 255, 255));
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selection();

			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "Codigo", "Nuit",
				"Telefone", "Email", "Endereco", "Provincia", "Distrito" }) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(2).setPreferredWidth(10);
		table.getColumnModel().getColumn(4).setPreferredWidth(15);

		txtSearch = new JTextField();
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setToolTipText("");
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					if (!txtSearch.getText().isEmpty()) {
						List<Cliente> clientes = clienteDao.findAll(txtSearch.getText(),empresa.getId());
						showInfo(clientes);
					} else
						showcliente();
				} catch (Exception ex) {

				}

			}
		});
		txtSearch.setBounds(18, 340, 796, 34);
		txtSearch.setColumns(10);
		panel.add(txtSearch);

		JLabel lblNewLabel = new JLabel("Pesquisar");
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel.setBounds(832, 340, 123, 32);
		panel.add(lblNewLabel);

		JLabel lblTipoDeReceita = new JLabel("Registo de Cliente");
		lblTipoDeReceita.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoDeReceita.setForeground(Color.BLUE);
		lblTipoDeReceita.setFont(new Font("Georgia", Font.BOLD, 18));
		lblTipoDeReceita.setBounds(10, 12, 963, 38);
		panel.add(lblTipoDeReceita);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setLayout(null);
		panel_3.setBorder(
				new TitledBorder(null, "Dados do Cliente" + "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(20, 38, 953, 245);
		panel.add(panel_3);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(84, 18, 338, 34);
		panel_3.add(txtId);

		JLabel lblNewLabel_1 = new JLabel("Codigo");
		lblNewLabel_1.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel_1.setBounds(463, 17, 70, 34);
		panel_3.add(lblNewLabel_1);

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(551, 18, 376, 34);
		panel_3.add(txtCodigo);

		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel_2.setBounds(7, 64, 70, 34);
		panel_3.add(lblNewLabel_2);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(84, 64, 338, 34);
		panel_3.add(txtNome);

		JLabel lblNewLabel_4 = new JLabel("Nuit");
		lblNewLabel_4.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel_4.setBounds(5, 110, 76, 34);
		panel_3.add(lblNewLabel_4);

		txtNuit = new JTextField();
		txtNuit.setColumns(10);
		txtNuit.setBounds(84, 111, 338, 34);
		panel_3.add(txtNuit);

		JLabel lblNewLabel_5 = new JLabel("Genero");
		lblNewLabel_5.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel_5.setBounds(748, 64, 76, 34);
		panel_3.add(lblNewLabel_5);

		cboGenero = new JComboBox<String>();
		cboGenero.setBounds(824, 64, 103, 34);
		panel_3.add(cboGenero);

		JLabel lblNewLabel_6 = new JLabel("Telefone");
		lblNewLabel_6.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel_6.setBounds(473, 110, 75, 34);
		panel_3.add(lblNewLabel_6);

		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(551, 111, 376, 34);
		panel_3.add(txtTelefone);

		JLabel lblNewLabel_7 = new JLabel("Endreco");
		lblNewLabel_7.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel_7.setBounds(7, 156, 70, 34);
		panel_3.add(lblNewLabel_7);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(84, 156, 338, 34);
		panel_3.add(txtEndereco);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Georgia", Font.BOLD, 14));
		lblEmail.setBounds(481, 156, 52, 34);
		panel_3.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(551, 156, 376, 34);
		panel_3.add(txtEmail);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Georgia", Font.BOLD, 14));
		lblId.setBounds(7, 18, 36, 19);
		panel_3.add(lblId);

		JLabel lblNewLabel_9 = new JLabel("Distrito");
		lblNewLabel_9.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel_9.setBounds(478, 209, 70, 24);
		panel_3.add(lblNewLabel_9);

		JLabel lblNewLabel_8 = new JLabel("Provincia");

		lblNewLabel_8.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel_8.setBounds(7, 202, 70, 24);
		panel_3.add(lblNewLabel_8);

		cboProvincia = new JComboBox<Provincia>();

		cboProvincia.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Provincia provincia = (Provincia) cboProvincia.getSelectedItem();
				List<Distrito> distritos = distritoDao.findAllByProvinciaName(provincia.getId());
				cboDistrito.removeAllItems();
				for (Distrito distrito : distritos) {
					cboDistrito.addItem(distrito);
				}
			}
		});
		cboProvincia.setBounds(84, 196, 338, 37);
		panel_3.add(cboProvincia);

		cboDistrito = new JComboBox<Distrito>();
		cboDistrito.setBounds(551, 202, 376, 37);
		panel_3.add(cboDistrito);
		/*----------------*/
		clienteDao = new ClienteDao();
		distritoDao = new DistritoDao();
		provinciaDao = new ProvinciaDao();
		empresa=FrmLogin.empresa;
		fillProvincia();
		fillGenero();
		showcliente();
	}

	/*-------------------------------------------*/
	private void fillProvincia() {
		List<Provincia> provincias = provinciaDao.findAll();
		for (Provincia provincia : provincias) {
			cboProvincia.addItem(provincia);
		}
	}

	private void fillGenero() {
		List<String> genero = new ArrayList<>();
		genero.add("M");
		genero.add("F");
		for (String string : genero)
			cboGenero.addItem(string);
	}

	/*------------------------------------------------*/
	private void save() {
		try {
			Cliente cliente = new Cliente();
			cliente.setEmpresa(empresa);
			String ID = txtId.getText();
			String nome = txtNome.getText();
			if (!nome.isEmpty()) {

				Provincia provincia = (Provincia) cboProvincia.getSelectedItem();
				Distrito distrito = (Distrito) cboDistrito.getSelectedItem();
				cliente.setCodigo(txtCodigo.getText());
				cliente.setNome(txtNome.getText());
				cliente.setEmail(txtEmail.getText());
				cliente.setEndereco(txtEndereco.getText());
				cliente.setTelefone(txtTelefone.getText());
				cliente.setNuit(txtNuit.getText());
				cliente.setGenero(cboGenero.getSelectedItem().toString());
				cliente.setProvincia(provincia);
				cliente.setDistrito(distrito);
				if (ID.equals("")) {
					cliente.setId(null);
				} else {
					cliente.setId(Long.valueOf(ID));
				}

				Cliente c = clienteDao.save(cliente);
				showcliente();
				clear();
				JOptionPane.showMessageDialog(rootPane, c.getNome() + " Registado com exito");

			} else {
				JOptionPane.showMessageDialog(null, "Introduza o Nome");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, o campo codigo nao pode ser duplicado:");
		}

	}

	//
	private void showcliente() {
		List<Cliente> clientes = clienteDao.findAll(empresa.getId());
		showInfo(clientes);
	}

	private void showInfo(List<Cliente> clientes) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] row = new Object[9];
		model.setRowCount(0);// empty the table
		for (int i = 0; i < clientes.size(); i++) {
			row[0] = clientes.get(i).getId();
			row[1] = clientes.get(i).getNome();
			row[2] = clientes.get(i).getCodigo();
			row[3] = clientes.get(i).getNuit();
			row[4] = clientes.get(i).getTelefone();
			row[5] = clientes.get(i).getEmail();
			row[6] = clientes.get(i).getEndereco();
			row[7] = clientes.get(i).getProvincia();
			row[8] = clientes.get(i).getDistrito();
			model.addRow(row);
		}

	}

	/*-------------------------------------*/
	private void selection() {
		try {
			int selectedRow = table.getSelectedRow();
			if (selectedRow >= 0) {
				Object id = table.getValueAt(selectedRow, 0);
				Object nome = table.getValueAt(selectedRow, 1);
				Object codigo = table.getValueAt(selectedRow, 2);
				Object nuit = table.getValueAt(selectedRow, 3);
				Object telefone = table.getValueAt(selectedRow, 4);
				Object email = table.getValueAt(selectedRow, 5);
				Object endereco = table.getValueAt(selectedRow, 6);
				Object provincia = table.getValueAt(selectedRow, 7);
				Object distrito = table.getValueAt(selectedRow, 8);

				txtId.setText(id.toString());
				txtNome.setText(nome.toString());
				txtCodigo.setText(codigo.toString());
				txtNuit.setText(nuit.toString());
				txtTelefone.setText(telefone.toString());
				txtEmail.setText(email.toString());
				txtEndereco.setText(endereco.toString());
				Distrito d = (Distrito) distrito;
				Provincia p = (Provincia) provincia;
				cboProvincia.removeAllItems();
				cboProvincia.addItem(p);

				cboDistrito.removeAllItems();
				cboDistrito.addItem(d);
			}
		} catch (NullPointerException ex) {

		}

	}

	private void clear() {
		txtId.setText("");
		txtCodigo.setText("");
		txtNome.setText("");
		txtNuit.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");
		txtEndereco.setText("");
		fillGenero();
		fillProvincia();

	}

	private void delete() {
		if (!txtId.getText().isEmpty()) {
			int confirmation = JOptionPane.showConfirmDialog(this, "Eliminar o Registo?",
					"Confirm Deletion", JOptionPane.YES_NO_OPTION);
			if (confirmation == JOptionPane.YES_OPTION) {
				clienteDao.deleteById(Long.valueOf(txtId.getText()));
				showcliente();
				clear();
			} else {
				JOptionPane.showMessageDialog(table, "Selecione a linha na tabela");
			}

		}

	}
}
