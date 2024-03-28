package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.meldev.dao.RoleDao;
import com.meldev.dao.UtilizadorDao;
import com.meldev.model.Empresa;
import com.meldev.model.Role;
import com.meldev.model.Utilizador;
import java.awt.Font;

public class FrmUtilizador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUtilizador;
	private JPasswordField txtPassword;
	private JTable table;
	private JComboBox<Role> cboPerfil;
	private JComboBox<String> cboStatus;
	private UtilizadorDao utilizadorDao;
	private RoleDao roleDao;
	private JTextField txtId;
	private Empresa empresa=null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmUtilizador frame = new FrmUtilizador();
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
	public FrmUtilizador() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 677, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Registo de Utilizador");
		lblNewLabel.setForeground(new Color(51, 102, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(8, 0, 643, 24);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Utilizador");
		lblNewLabel_1.setBounds(29, 60, 90, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(364, 60, 70, 15);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Perfil");
		lblNewLabel_3.setBounds(29, 100, 70, 15);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Status");
		lblNewLabel_4.setBounds(364, 100, 70, 15);
		contentPane.add(lblNewLabel_4);

		txtUtilizador = new JTextField();
		txtUtilizador.setBounds(113, 58, 214, 24);
		contentPane.add(txtUtilizador);
		txtUtilizador.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(445, 58, 210, 19);
		contentPane.add(txtPassword);

		cboStatus = new JComboBox<>();
		cboStatus.setBounds(452, 95, 199, 24);
		contentPane.add(cboStatus);

		cboPerfil = new JComboBox<>();
		cboPerfil.setBounds(117, 87, 210, 24);
		contentPane.add(cboPerfil);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		btnGravar.setBounds(113, 134, 117, 25);
		contentPane.add(btnGravar);

		JButton btnDelete = new JButton("Apagar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDelete.setBounds(489, 131, 117, 25);
		contentPane.add(btnDelete);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(50, 206, 580, 168);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setForeground(new Color(0, 0, 0));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selection();
			}
		});
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Utilizador", "Status", "Perfil" }) {
					private static final long serialVersionUID = 1L;
					@SuppressWarnings("rawtypes")
					Class[] columnTypes = new Class[] { Long.class, String.class, String.class, Object.class };

					public Class<?> getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		 table.getColumnModel().getColumn(0).setPreferredWidth(15);
		scrollPane.setViewportView(table);

		txtId = new JTextField();
		txtId.setForeground(Color.RED);
		txtId.setEditable(false);
		txtId.setToolTipText("");
		txtId.setBounds(113, 36, 114, 19);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		
	
		/*---------------------*/
		utilizadorDao = new UtilizadorDao();
		roleDao = new RoleDao();
		empresa=FrmLogin.empresa;
		fillRoles();
		fillStatus();
		showutilizador();

	}

	/**/
	private void fillRoles() {
		List<Role> roles = roleDao.findAll();
		cboPerfil.removeAllItems();
		for (Role role : roles) {
			cboPerfil.addItem(role);
		}

	}

	private void fillStatus() {
		List<String> estados = new ArrayList<>();
		estados.add("ACTIVO");
		estados.add("INACTIVO");
		for (String string : estados) {
			cboStatus.addItem(string);
		}

	}

	/**/
	private void delete() {
		if (!txtId.getText().isEmpty()) {
			int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?",
					"Confirm Deletion", JOptionPane.YES_NO_OPTION);
			if (confirmation == JOptionPane.YES_OPTION) {
				utilizadorDao.deleteById(Long.valueOf(txtId.getText()));
				showutilizador();
				clear();
			} else {
				JOptionPane.showMessageDialog(table, "Selecione a linha na tabela");
			}

		}

	}

	/**/
	
	@SuppressWarnings("deprecation")
	private void save() {

		try {
			Utilizador utilizador = new Utilizador();
			String ID = txtId.getText();
			String nome = txtUtilizador.getText();
			if (!nome.isEmpty()) {
				Role role = (Role) cboPerfil.getSelectedItem();
				utilizador.setStatus(cboStatus.getSelectedItem().toString());
				utilizador.setUsername(txtUtilizador.getText());
				utilizador.setPassword(txtPassword.getText());
				utilizador.setEmpresa(empresa);
				utilizador.setRole(role);
				if (ID.equals("")) {
					utilizador.setId(null);
					
				} else {
					utilizador.setId(Long.valueOf(ID));
				}

				utilizadorDao.save(utilizador);
				JOptionPane.showMessageDialog(rootPane, " Utilizador Registada com exito");
				showutilizador();
				txtId.setText("");

			} else {
				JOptionPane.showMessageDialog(null, "Introduza o nome do utilizador");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro, Algo estranho ocorreu");
			e.printStackTrace();
		}

	}

	//
	private void showutilizador() {
		List<Utilizador> utilizador = utilizadorDao.findAll(empresa.getId());
		showInfo(utilizador);
	}

	private void showInfo(List<Utilizador> utilizador) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] row = new Object[4];
		model.setRowCount(0);// empty the table
		for (int i = 0; i < utilizador.size(); i++) {
			row[0] = utilizador.get(i).getId();
			row[1] = utilizador.get(i).getUsername();
			row[2] = utilizador.get(i).getStatus();
			row[3] = utilizador.get(i).getRole();

			model.addRow(row);
		}

	}

	/*-------------------------------------*/
	private void selection() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Object id = table.getValueAt(selectedRow, 0);
			Object username = table.getValueAt(selectedRow, 1);
			Object status = table.getValueAt(selectedRow, 2);
			Object perfil = table.getValueAt(selectedRow, 3);
			txtId.setText(id.toString());
			txtUtilizador.setText(username.toString());
			cboStatus.removeAllItems();
			cboStatus.addItem(status.toString());
			cboPerfil.removeAllItems();
			Role role = (Role) perfil;
			cboPerfil.addItem(role);

		}

	}

	private void clear() {
		txtUtilizador.setText("");
		txtPassword.setText("");
	}

}
