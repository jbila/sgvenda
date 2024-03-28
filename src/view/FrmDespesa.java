package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.meldev.dao.ContaDao;
import com.meldev.dao.DespesaDao;
import com.meldev.dao.TipoDespesaDao;
import com.meldev.model.Conta;
import com.meldev.model.Despesa;
import com.meldev.model.Empresa;
import com.meldev.model.TipoDespesa;
import com.meldev.reports.Reports;

public class FrmDespesa extends JFrame {

	private static final long serialVersionUID = 1L;
	private DespesaDao despesaDao;
	private ContaDao contaDao;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtDescricao;
	private JTextField txtValor;
	private JComboBox<TipoDespesa> cboTipoDespesa;
	private JComboBox<String> cboCategoria;
	private JComboBox<Integer> cboAno;
	private JComboBox<String> cboMes;
	private JComboBox<String> cboStatus;
	private JComboBox<Conta> cboConta;
	private Empresa empresa = null;

	private JTable table;
	private JTextField txtSearch;
	private TipoDespesaDao tipoDespesaDao;
	private JTextField txtTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDespesa frame = new FrmDespesa();
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
	public FrmDespesa() {
		setBackground(UIManager.getColor("desktop"));
		setResizable(false);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 959, 740);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("desktop"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("desktop"));
		panel.setBounds(12, -7, 935, 710);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblRegistoDedespesa = new JLabel("Registo de Despesa");
		lblRegistoDedespesa.setForeground(new Color(51, 102, 255));
		lblRegistoDedespesa.setBounds(12, 2, 879, 41);
		lblRegistoDedespesa.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistoDedespesa.setFont(new Font("Dialog", Font.BOLD, 18));
		panel.add(lblRegistoDedespesa);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 58, 881, 178);
		panel.add(panel_1);
		panel_1.setLayout(null);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(115, 4, 169, 34);
		panel_1.add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Descricao");
		lblNewLabel_1.setBounds(294, 3, 70, 34);
		panel_1.add(lblNewLabel_1);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(371, 4, 498, 34);
		panel_1.add(txtDescricao);
		txtDescricao.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Valor");
		lblNewLabel_2.setBounds(2, 50, 70, 34);
		panel_1.add(lblNewLabel_2);

		txtValor = new JTextField();
		txtValor.setBounds(115, 50, 169, 34);
		panel_1.add(txtValor);
		txtValor.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setBounds(307, 50, 55, 34);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("Categoria");
		lblNewLabel_5.setBounds(532, 50, 76, 34);
		panel_1.add(lblNewLabel_5);

		cboCategoria = new JComboBox<>();
		cboCategoria.setBounds(626, 50, 243, 34);
		panel_1.add(cboCategoria);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(2, 8, 14, 15);
		panel_1.add(lblId);

		JLabel lblNewLabel_9 = new JLabel("Mes");
		lblNewLabel_9.setBounds(509, 116, 49, 34);
		panel_1.add(lblNewLabel_9);

		JLabel lblNewLabel_8 = new JLabel("Tipo Despesa");
		lblNewLabel_8.setBounds(2, 116, 105, 24);
		panel_1.add(lblNewLabel_8);
		cboTipoDespesa = new JComboBox<>();
		cboTipoDespesa.setBounds(115, 113, 169, 34);
		panel_1.add(cboTipoDespesa);

		cboMes = new JComboBox<String>();
		cboMes.setBounds(560, 116, 116, 34);
		panel_1.add(cboMes);

		JLabel lblNewLabel_9_1 = new JLabel("Ano");
		lblNewLabel_9_1.setBounds(694, 111, 55, 34);
		panel_1.add(lblNewLabel_9_1);

		cboAno = new JComboBox<Integer>();
		cboAno.setBounds(750, 111, 119, 34);
		panel_1.add(cboAno);

		cboStatus = new JComboBox<String>();
		cboStatus.setBounds(371, 52, 129, 34);
		panel_1.add(cboStatus);

		cboConta = new JComboBox<>();
		cboConta.setBounds(371, 116, 129, 34);
		panel_1.add(cboConta);

		JLabel lblNewLabel_9_2 = new JLabel("Banco");
		lblNewLabel_9_2.setBounds(307, 116, 49, 34);
		panel_1.add(lblNewLabel_9_2);
		cboTipoDespesa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBorder(new TitledBorder(null, "Acções ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(0, 237, 364, 56);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JButton btnSave = new JButton("Gravar");
		btnSave.setBounds(12, 17, 81, 25);
		panel_2.add(btnSave);
		btnSave.setBackground(UIManager.getColor("info"));

		JButton btnDelete = new JButton("Apagar");
		btnDelete.setBounds(234, 17, 117, 25);
		panel_2.add(btnDelete);

		JButton btnLoad = new JButton("carregar");
		btnLoad.setBounds(105, 17, 117, 25);
		panel_2.add(btnLoad);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillCombos();
			}
		});
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
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(12, 345, 889, 326);

		panel.add(scrollPane);

		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setFont(new Font("SansSerif", Font.PLAIN, 14));
		// table.setFillsViewportHeight(true);
		// table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selection();

			}
		});
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Categoria", "Tipo da Despesa",
				"Descricao", "Estado", "Valor", "Mes", "Ano", "Banco" }) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Long.class, String.class, Object.class, String.class, String.class,
					String.class, String.class, String.class, String.class };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(170);
		table.getColumnModel().getColumn(3).setPreferredWidth(170);
		table.getColumnModel().getColumn(4).setPreferredWidth(15);
		table.getColumnModel().getColumn(7).setPreferredWidth(18);
		table.getColumnModel().getColumn(8).setPreferredWidth(10);
		scrollPane.setViewportView(table);

		txtSearch = new JTextField();
		txtSearch.setBounds(20, 303, 293, 34);
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setToolTipText("");
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtSearch.getText().isEmpty()) {
					List<Despesa> despesas = despesaDao.findAll(txtSearch.getText(), empresa.getId());
					showInfo(despesas);
				} else
					showdespesa();

			}
		});
		txtSearch.setColumns(10);
		panel.add(txtSearch);

		JLabel lblNewLabel = new JLabel("Pesquisar");
		lblNewLabel.setBounds(318, 305, 123, 32);
		panel.add(lblNewLabel);

		txtTotal = new JTextField();
		txtTotal.setText("");
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setForeground(Color.RED);
		txtTotal.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(575, 672, 185, 30);
		panel.add(txtTotal);

		JLabel lblNewLabel_4_4_1 = new JLabel("Sub-Total");
		lblNewLabel_4_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_4_1.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel_4_4_1.setBounds(466, 672, 102, 28);
		panel.add(lblNewLabel_4_4_1);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(null, "Pesquisa ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2_1.setBackground(Color.WHITE);
		panel_2_1.setBounds(398, 248, 488, 56);
		panel.add(panel_2_1);

		JButton btnSearchByMonthAndYear = new JButton("mes&ano");
		btnSearchByMonthAndYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*-----------------------------------*/
				try {
					Reports report;
					report = new Reports();
					report.incomeReportByMonthAndYear(cboMes.getSelectedItem().toString(),
							Integer.valueOf(cboAno.getSelectedItem().toString()), empresa.getId());
				} catch (SQLException e1) {

				}

			}
		});
		btnSearchByMonthAndYear.setForeground(Color.BLUE);
		btnSearchByMonthAndYear.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnSearchByMonthAndYear.setBounds(255, 17, 120, 25);
		panel_2_1.add(btnSearchByMonthAndYear);

		JButton btnSearchByYear = new JButton("ano");
		btnSearchByYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*------------------------------------*/

				try {
					Reports report;
					report = new Reports();
					report.incomeReportByYear(Integer.valueOf(cboAno.getSelectedItem().toString()), empresa.getId());
				} catch (SQLException e1) {

				}

			}
		});
		btnSearchByYear.setForeground(Color.BLUE);
		btnSearchByYear.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnSearchByYear.setBounds(387, 17, 89, 25);
		panel_2_1.add(btnSearchByYear);

		JButton btnPreviewYear = new JButton("ver ano");
		btnPreviewYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*------------------------------------------*/
				List<Despesa> despesas = despesaDao.findByAno(Integer.valueOf(cboAno.getSelectedItem().toString()),
						empresa.getId());
				showInfo(despesas);
			}
		});
		btnPreviewYear.setForeground(Color.BLUE);
		btnPreviewYear.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnPreviewYear.setBounds(142, 17, 98, 25);
		panel_2_1.add(btnPreviewYear);

		JButton bntPrevieYearMonth = new JButton("ver mes&ano");
		bntPrevieYearMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*------------------------*/
				List<Despesa> despesas = despesaDao.findByAnoAndMes(
						Integer.valueOf(cboAno.getSelectedItem().toString()), cboMes.getSelectedItem().toString(),
						empresa.getId());
				showInfo(despesas);
			}
		});
		bntPrevieYearMonth.setForeground(Color.BLUE);
		bntPrevieYearMonth.setFont(new Font("Dialog", Font.PLAIN, 12));
		bntPrevieYearMonth.setBounds(10, 16, 124, 25);
		panel_2_1.add(bntPrevieYearMonth);
		/*----------------*/
		contaDao = new ContaDao();
		despesaDao = new DespesaDao();
		tipoDespesaDao = new TipoDespesaDao();
		empresa = FrmLogin.empresa;
		fillAno();
		fillTipoDespesa();
		fillMes();
		fillStatus();
		showdespesa();
		fillCategoria();
		fillConta();
	}

	private void fillCombos() {
		fillAno();
		fillTipoDespesa();
		fillMes();
		fillStatus();
		fillCategoria();
		fillConta();
	}

	/*-------------------------------------------*/
	private void fillTipoDespesa() {
		List<TipoDespesa> tipoDespesas = tipoDespesaDao.findAll(empresa.getId());
		cboTipoDespesa.removeAllItems();
		for (TipoDespesa tipoDespesa : tipoDespesas) {
			cboTipoDespesa.addItem(tipoDespesa);
		}
	}

	private void fillConta() {
		List<Conta> contas = contaDao.findAll(empresa.getId());
		cboConta.removeAllItems();
		for (Conta conta : contas) {
			cboConta.addItem(conta);
		}
	}

	private void fillCategoria() {
		List<String> categorias = new ArrayList<>();
		cboCategoria.removeAllItems();
		categorias.add("Fixa");
		categorias.add("Variavel");
		for (String string : categorias)
			cboCategoria.addItem(string);
	}

	private void fillMes() {
		List<String> meses = new ArrayList<>();
		cboMes.removeAllItems();
		meses.add("JANEIRO");
		meses.add("FEVEREIRO");
		meses.add("MARCO");
		meses.add("ABRIL");
		meses.add("MAIO");
		meses.add("JUNHO");
		meses.add("JULHO");
		meses.add("AGOSTO");
		meses.add("SETEMBRO");
		meses.add("OUTUBRO");
		meses.add("NOVEMBRO");
		meses.add("DEZEMBRO");
		for (String string : meses)
			cboMes.addItem(string);
	}

	private void fillAno() {
		List<Integer> anos = new ArrayList<>();
		cboAno.removeAllItems();
		anos.add(2023);
		anos.add(2022);
		anos.add(2021);
		anos.add(2020);
		anos.add(2019);
		for (Integer ano : anos)
			cboAno.addItem(ano);
	}

	private void fillStatus() {
		List<String> estado = new ArrayList<>();
		cboStatus.removeAllItems();
		estado.add("PAGO");
		estado.add("NAO-PAGO");
		for (String ano : estado)
			cboStatus.addItem(ano);
	}

	/*------------------------------------------------*/
	private void save() {

		try {
			Despesa despesa = new Despesa();
			String ID = txtId.getText();
			String nome = txtValor.getText();
			despesa.setEmpresa(empresa);
			if (!nome.isEmpty()) {

				TipoDespesa tipoDespesa = (TipoDespesa) cboTipoDespesa.getSelectedItem();
				Conta conta = (Conta) cboConta.getSelectedItem();
				despesa.setValor(Double.valueOf(txtValor.getText()));
				despesa.setCategoria(cboCategoria.getSelectedItem().toString());
				despesa.setTipoDespesa(tipoDespesa);
				despesa.setCategoria(cboCategoria.getSelectedItem().toString());
				despesa.setAno(Integer.parseInt(cboAno.getSelectedItem().toString()));
				despesa.setMesReferente(cboMes.getSelectedItem().toString());
				despesa.setStatus(cboStatus.getSelectedItem().toString());
				despesa.setDescricao(txtDescricao.getText());
				despesa.setConta(conta);

				if (ID.equals("")) {
					despesa.setId(null);
				} else {
					despesa.setId(Long.valueOf(ID));
				}

				despesaDao.save(despesa);
				JOptionPane.showMessageDialog(rootPane, " Despesa Registada com exito");
				showdespesa();
				txtValor.setText("");
				txtId.setText("");

			} else {
				JOptionPane.showMessageDialog(null, "Introduza uma descricao da despesa");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, Algo estranho ocorreu");
			e.printStackTrace();
		}

	}

	//
	private void showdespesa() {
		List<Despesa> despesas = despesaDao.findAll(empresa.getId());
		showInfo(despesas);
	}

	private void showInfo(List<Despesa> despesas) {
		txtTotal.setText("");
		double total = 0.0;
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		Object[] row = new Object[9];
		model.setRowCount(0);// empty the table
		for (int i = 0; i < despesas.size(); i++) {
			row[0] = despesas.get(i).getId();
			row[1] = despesas.get(i).getCategoria();
			row[2] = despesas.get(i).getTipoDespesa();
			row[3] = despesas.get(i).getDescricao();
			row[4] = despesas.get(i).getStatus();
			row[5] = despesas.get(i).getValor();
			row[6] = despesas.get(i).getMesReferente();
			row[7] = despesas.get(i).getAno();
			row[8] = despesas.get(i).getConta().getNome();
			total += Double.valueOf(despesas.get(i).getValor());
			model.addRow(row);
		}
		/*----------------------------------------------*/
		Locale mozambiqueLocale = new Locale("pt", "MZ"); // Portuguese language in Mozambique
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(mozambiqueLocale);
		// Format the number as currency
		String formattedCurrency = currencyFormat.format(total);
		txtTotal.setText(formattedCurrency);
		/*----------------------------------------------*/
	}

	/*-------------------------------------*/
	private void selection() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Object id = table.getValueAt(selectedRow, 0);
			Object categoria = table.getValueAt(selectedRow, 1);
			Object tipoDespesa = table.getValueAt(selectedRow, 2);
			Object descricao = table.getValueAt(selectedRow, 3);
			Object estado = table.getValueAt(selectedRow, 4);
			Object valor = table.getValueAt(selectedRow, 5);
			Object mes = table.getValueAt(selectedRow, 6);
			Object ano = table.getValueAt(selectedRow, 7);

			txtId.setText(id.toString());
			txtValor.setText(valor.toString());
			txtDescricao.setText(descricao.toString());
			TipoDespesa tipoDespesa2 = (TipoDespesa) tipoDespesa;
			cboTipoDespesa.removeAllItems();
			cboStatus.addItem(estado.toString());
			cboTipoDespesa.addItem(tipoDespesa2);
			cboMes.removeAllItems();
			cboMes.addItem(mes.toString());
			cboAno.removeAllItems();
			cboAno.addItem(Integer.parseInt(ano.toString()));
			cboCategoria.addItem(categoria.toString());

		}

	}

	private void clear() {
		txtId.setText("");
		txtDescricao.setText("");
		txtValor.setText("");
		fillAno();
		fillCategoria();
		fillMes();
		fillTipoDespesa();

	}

	private void delete() {
		if (!txtId.getText().isEmpty()) {
			int confirmation = JOptionPane.showConfirmDialog(this, "Eliminar o Registo ?", "Confirm Deletion",
					JOptionPane.YES_NO_OPTION);
			if (confirmation == JOptionPane.YES_OPTION) {
				despesaDao.deleteById(Long.valueOf(txtId.getText()));
				showdespesa();
				clear();
			} else {
				JOptionPane.showMessageDialog(table, "Selecione a linha na tabela");
			}

		}

	}
}
