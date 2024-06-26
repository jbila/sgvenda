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

import com.meldev.dao.FuncaoDao;
import com.meldev.model.Empresa;
import com.meldev.model.Funcao;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

public class FrmTipoDocumento extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtSearch;
	private JTable table;
	private FuncaoDao funcaoDao;
	private Empresa empresa=null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmTipoDocumento frame = new FrmTipoDocumento();
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
	public FrmTipoDocumento() {
		setBackground(UIManager.getColor("Desktop.background"));
		setTitle("Funcao");
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

		JLabel lblTipoDeDespesa = new JLabel("Funcao");
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

			}
		});
		btnSave.setBounds(500, 94, 117, 31);
		panel.add(btnSave);

		JButton btnDelete = new JButton("Apagar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
				clear();
				showFuncao();
			}
		});
		btnDelete.setBounds(500, 152, 117, 28);
		panel.add(btnDelete);

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtSearch.getText().isEmpty()) {
					List<Funcao> funcoes = funcaoDao.findAll(txtSearch.getText(),empresa.getId());
					showInfo(funcoes);

				} else
					showFuncao();
			}
		});
		txtSearch.setBounds(78, 152, 393, 32);
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
		
		JLabel lblTipoDeReceita = new JLabel("Funcao");
		lblTipoDeReceita.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoDeReceita.setForeground(Color.BLUE);
		lblTipoDeReceita.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTipoDeReceita.setBounds(12, 23, 643, 38);
		panel.add(lblTipoDeReceita);
		funcaoDao = new FuncaoDao();
		showFuncao();
		empresa=FrmLogin.empresa;
	}

	/**/
	private void save() {
		String nome = txtNome.getText().trim(); // Remove espaços em branco extras
		String ID = txtId.getText();
		Funcao funcao = new Funcao();

		if (!nome.isEmpty()) {
			funcao.setNome(nome);
			funcao.setDescricao(txtNome.getText()); // Use txtDescricao em vez de txtNome
			if (ID.equals("")) {
				funcao.setId(null);
			} else {
				funcao.setId(Long.valueOf(ID));
			}

			Funcao cat = funcaoDao.save(funcao);
			JOptionPane.showMessageDialog(rootPane, cat.getNome() + " Saved");
			showFuncao();
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
				funcaoDao.deleteById(Long.valueOf(txtId.getText()));
				showFuncao();
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
	private void showFuncao() {
		List<Funcao> funcoes = funcaoDao.findAll(empresa.getId());
		showInfo(funcoes);
	}

	private void showInfo(List<Funcao> funcoes) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] row = new Object[3];
		model.setRowCount(0);// empty the table
		for (int i = 0; i < funcoes.size(); i++) {
			row[0] = funcoes.get(i).getId();
			row[1] = funcoes.get(i).getNome();
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
