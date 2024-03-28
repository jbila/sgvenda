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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.meldev.dao.ContaDao;
import com.meldev.dao.ReceitaDao;
import com.meldev.dao.TipoReceitaDao;
import com.meldev.model.Conta;
import com.meldev.model.Empresa;
import com.meldev.model.Receita;
import com.meldev.model.TipoReceita;
import com.meldev.reports.Reports;

public class FrmReceita extends JFrame {

	private static final long serialVersionUID = 1L;
	private ReceitaDao ReceitaDao;
	private ContaDao contaDao;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtDescricao;
	private JTextField txtValor;
	private JComboBox<TipoReceita> cboTipoReceita;
	private JComboBox<String> cboCategoria;
	private JComboBox<Integer> cboAno;
	private JComboBox<String> cboMes;
	private JComboBox<String> cboStatus;
	private JComboBox<Conta> cboConta;
	private JTable table;
	private JTextField txtSearch;
	private TipoReceitaDao tipoReceitaDao;
	private JTextField txtTotal;
	private Empresa empresa = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReceita frame = new FrmReceita();
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
	public FrmReceita() {
		setResizable(false);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 983, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, -7, 935, 683);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblRegistoDeReceita = new JLabel("Registo de  Receita");
		lblRegistoDeReceita.setForeground(new Color(51, 102, 255));
		lblRegistoDeReceita.setBounds(12, -1, 923, 55);
		lblRegistoDeReceita.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistoDeReceita.setFont(new Font("Dialog", Font.BOLD, 18));
		panel.add(lblRegistoDeReceita);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 58, 881, 169);
		panel.add(panel_1);
		panel_1.setLayout(null);

		txtId = new JTextField();
		txtId.setForeground(new Color(255, 0, 0));
		txtId.setBounds(79, 4, 182, 34);
		panel_1.add(txtId);
		txtId.setEditable(false);
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
		txtValor.setBounds(115, 50, 192, 34);
		panel_1.add(txtValor);
		txtValor.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setBounds(325, 50, 55, 34);
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

		JLabel lblNewLabel_8 = new JLabel("Empresa");
		lblNewLabel_8.setBounds(2, 116, 105, 24);
		panel_1.add(lblNewLabel_8);
		cboTipoReceita = new JComboBox<>();
		cboTipoReceita.setBounds(115, 110, 192, 37);
		panel_1.add(cboTipoReceita);

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
		cboStatus.setBounds(381, 52, 119, 34);
		panel_1.add(cboStatus);

		cboConta = new JComboBox<>();
		cboConta.setBounds(384, 116, 116, 34);
		panel_1.add(cboConta);

		JLabel lblNewLabel_9_2 = new JLabel("Banco");
		lblNewLabel_9_2.setBounds(325, 116, 49, 34);
		panel_1.add(lblNewLabel_9_2);
		cboTipoReceita.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBorder(new TitledBorder(null, "Acções ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(12, 239, 368, 50);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JButton btnSave = new JButton("Gravar");
		btnSave.setForeground(Color.BLUE);
		btnSave.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnSave.setBounds(5, 17, 87, 25);
		panel_2.add(btnSave);

		JButton btnDelete = new JButton("Apagar");
		btnDelete.setForeground(Color.BLUE);
		btnDelete.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnDelete.setBounds(269, 17, 87, 25);
		panel_2.add(btnDelete);

		JButton btnLoad = new JButton("Carregar");
		btnLoad.setForeground(Color.BLUE);
		btnLoad.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnLoad.setBounds(130, 17, 104, 25);
		panel_2.add(btnLoad);
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillCombo();
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
		scrollPane.setBounds(20, 345, 881, 291);

		panel.add(scrollPane);

		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setForeground(new Color(0, 0, 0));
		table.setFont(new Font("SansSerif", Font.PLAIN, 14));
		table.setFillsViewportHeight(true);

		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selection();

			}
		});

		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Empresa", "Descricao", "Categoria", "Estado", "Valor", "Mes", "Ano", "Banco" }) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(170);
		table.getColumnModel().getColumn(3).setPreferredWidth(15);
		table.getColumnModel().getColumn(4).setPreferredWidth(15);
		table.getColumnModel().getColumn(7).setPreferredWidth(18);
		table.getColumnModel().getColumn(8).setPreferredWidth(10);
		scrollPane.setViewportView(table);

		txtSearch = new JTextField();
		txtSearch.setBounds(106, 299, 274, 34);
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setToolTipText("");
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtSearch.getText().isEmpty()) {
					List<Receita> Receitas = ReceitaDao.findAll(txtSearch.getText(), empresa.getId());
					showInfo(Receitas);
				} else
					showReceita();

			}
		});
		txtSearch.setColumns(10);
		panel.add(txtSearch);

		txtTotal = new JTextField();
		txtTotal.setText("");
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setForeground(Color.RED);
		txtTotal.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(512, 641, 185, 30);
		panel.add(txtTotal);

		JLabel lblNewLabel_4_4_1 = new JLabel("Sub-Total");
		lblNewLabel_4_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_4_1.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel_4_4_1.setBounds(406, 641, 102, 28);
		panel.add(lblNewLabel_4_4_1);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(null, "Pesquisa ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2_1.setBackground(Color.WHITE);
		panel_2_1.setBounds(405, 239, 488, 94);
		panel.add(panel_2_1);

		JButton btnSearchByMonthAndYear = new JButton("mes&ano");
		btnSearchByMonthAndYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/* filter by year */
				Reports report;
				try {
					report = new Reports();
					report.incomeReportByMonthAndYear(cboMes.getSelectedItem().toString(),
							Integer.valueOf(cboAno.getSelectedItem().toString()), empresa.getId());

				} catch (SQLException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnSearchByMonthAndYear.setForeground(Color.BLUE);
		btnSearchByMonthAndYear.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnSearchByMonthAndYear.setBounds(255, 17, 120, 25);
		panel_2_1.add(btnSearchByMonthAndYear);

		JButton btnSearchByYear = new JButton("ano");
		btnSearchByYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/* filter by year */
				Reports report;
				try {
					report = new Reports();
					report.incomeReportByYear(Integer.valueOf(cboAno.getSelectedItem().toString()), empresa.getId());

				} catch (SQLException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnSearchByYear.setBounds(387, 17, 89, 25);
		panel_2_1.add(btnSearchByYear);
		btnSearchByYear.setForeground(Color.BLUE);
		btnSearchByYear.setFont(new Font("Dialog", Font.PLAIN, 12));

		JButton btnPreviewYear = new JButton("ver ano");
		btnPreviewYear.setForeground(Color.BLUE);
		btnPreviewYear.setBounds(142, 17, 98, 25);
		panel_2_1.add(btnPreviewYear);
		btnPreviewYear.setFont(new Font("Dialog", Font.PLAIN, 12));

		JButton bntPrevieYearMonth = new JButton("ver mes&ano");
		bntPrevieYearMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/* chama uma funcao que gera relatorio por empresa e ano */
				List<Receita> Receitas = ReceitaDao.findByAnoAndMes(
						Integer.valueOf(cboAno.getSelectedItem().toString()), cboMes.getSelectedItem().toString(),
						empresa.getId());
				showInfo(Receitas);
			}
		});
		bntPrevieYearMonth.setForeground(Color.BLUE);
		bntPrevieYearMonth.setFont(new Font("Dialog", Font.PLAIN, 12));
		bntPrevieYearMonth.setBounds(10, 16, 124, 25);
		panel_2_1.add(bntPrevieYearMonth);

		JLabel lblNewLabel = new JLabel("Pesquisar");
		lblNewLabel.setBounds(22, 301, 82, 32);
		panel.add(lblNewLabel);
		btnPreviewYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Receita> Receitas = ReceitaDao.findByAno(Integer.valueOf(cboAno.getSelectedItem().toString()),
						empresa.getId());
				showInfo(Receitas);
			}
		});
		/*----------------*/
		contaDao = new ContaDao();
		ReceitaDao = new ReceitaDao();
		tipoReceitaDao = new TipoReceitaDao();

		empresa = FrmLogin.empresa;
		fillAno();
		fillTipoReceita();
		fillMes();
		fillStatus();
		showReceita();
		fillCategoria();
		fillConta();
	}

	/*-------------------------------------------*/
	private void fillTipoReceita() {
		List<TipoReceita> tipoReceitas = tipoReceitaDao.findAll(empresa.getId());
		cboTipoReceita.removeAllItems();
		for (TipoReceita tipoReceita : tipoReceitas) {
			cboTipoReceita.addItem(tipoReceita);
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
			Receita Receita = new Receita();
			Receita.setEmpresa(empresa);
			String ID = txtId.getText();
			String nome = txtValor.getText();
			if (!nome.isEmpty()) {

				TipoReceita tipoReceita = (TipoReceita) cboTipoReceita.getSelectedItem();
				Conta conta = (Conta) cboConta.getSelectedItem();
				Receita.setValor(Double.valueOf(txtValor.getText()));
				Receita.setCategoria(cboCategoria.getSelectedItem().toString());
				Receita.setTipoReceita(tipoReceita);
				Receita.setCategoria(cboCategoria.getSelectedItem().toString());
				Receita.setAno(Integer.parseInt(cboAno.getSelectedItem().toString()));
				Receita.setMesReferente(cboMes.getSelectedItem().toString());
				Receita.setStatus(cboStatus.getSelectedItem().toString());
				Receita.setDescricao(txtDescricao.getText());
				Receita.setConta(conta);

				if (ID.equals("")) {
					Receita.setId(null);
				} else {
					Receita.setId(Long.valueOf(ID));
				}

				ReceitaDao.save(Receita);
				JOptionPane.showMessageDialog(rootPane, " Receita Registada com exito");
				showReceita();
				txtValor.setText("");
				txtId.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "Introduza uma descricao da Receita");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro, Algo estranho ocorreu");
			e.printStackTrace();
		}

	}

	//
	private void showReceita() {
		List<Receita> Receitas = ReceitaDao.findAll(empresa.getId());
		showInfo(Receitas);
	}

	private void showInfo(List<Receita> receitas) {
		txtTotal.setText("");
		double total = 0.0;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] row = new Object[9];
		model.setRowCount(0);// empty the table
		for (int i = 0; i < receitas.size(); i++) {
			row[0] = receitas.get(i).getId();
			row[1] = receitas.get(i).getTipoReceita();
			row[2] = receitas.get(i).getDescricao();
			row[3] = receitas.get(i).getCategoria();
			row[4] = receitas.get(i).getStatus();
			row[5] = receitas.get(i).getValor();
			row[6] = receitas.get(i).getMesReferente();
			row[7] = receitas.get(i).getAno();
			row[8] = receitas.get(i).getConta().getNome();
			total += Double.valueOf(receitas.get(i).getValor());
			model.addRow(row);
		}

		Locale mozambiqueLocale = new Locale("pt", "MZ"); // Portuguese language in Mozambique
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(mozambiqueLocale);
		// Format the number as currency
		String formattedCurrency = currencyFormat.format(total);
		txtTotal.setText(formattedCurrency);
		/*-----------------------------------------------------*/

	}

	/*-------------------------------------*/
	private void selection() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Object id = table.getValueAt(selectedRow, 0);
			Object tipoReceita = table.getValueAt(selectedRow, 1);
			Object descricao = table.getValueAt(selectedRow, 2);
			Object categoria = table.getValueAt(selectedRow, 3);
			Object estado = table.getValueAt(selectedRow, 4);
			Object valor = table.getValueAt(selectedRow, 5);
			Object mes = table.getValueAt(selectedRow, 6);
			Object ano = table.getValueAt(selectedRow, 7);

			txtId.setText(id.toString());
			txtValor.setText(valor.toString());
			txtDescricao.setText(descricao.toString());
			TipoReceita tipoReceita2 = (TipoReceita) tipoReceita;
			cboTipoReceita.removeAllItems();
			cboTipoReceita.addItem(tipoReceita2);
			cboStatus.removeAllItems();
			cboStatus.addItem(estado.toString());

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
		fillTipoReceita();

	}

	private void fillCombo() {
		fillAno();
		fillCategoria();
		fillConta();
		fillTipoReceita();
		fillMes();

	}

	private void delete() {
		if (!txtId.getText().isEmpty()) {
			int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?",
					"Confirm Deletion", JOptionPane.YES_NO_OPTION);
			if (confirmation == JOptionPane.YES_OPTION) {
				ReceitaDao.deleteById(Long.valueOf(txtId.getText()));
				showReceita();
				clear();
			} else {
				JOptionPane.showMessageDialog(table, "Selecione a linha na tabela");
			}

		}

	}
}
