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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.meldev.dao.PatrimonioDao;
import com.meldev.model.Empresa;
import com.meldev.model.Patrimonio;
import com.toedter.calendar.JDateChooser;

public class FrmPatrimonio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PatrimonioDao patrimonioDao;
	private JTextField txtId;
	private JTextField txtDescricao;
	private JTextField txtValor;
	private JComboBox<String> cboTipo;
	private JComboBox<String> cboStatus;
	private JTable table;
	private JTextField txtSearch;
	private JTextField txtQty;
	private JTextField txtTotal;
	private JTextField txtCodigo;
	private JDateChooser dateChooser;
	private Empresa empresa=null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPatrimonio frame = new FrmPatrimonio();
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
	public FrmPatrimonio() {
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

		JLabel lblRegistoDepatrimonio = new JLabel("Registo de  Patrimonio");
		lblRegistoDepatrimonio.setBackground(new Color(51, 153, 255));
		lblRegistoDepatrimonio.setForeground(new Color(51, 102, 255));
		lblRegistoDepatrimonio.setBounds(12, 2, 923, 41);
		lblRegistoDepatrimonio.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistoDepatrimonio.setFont(new Font("Dialog", Font.BOLD, 18));
		panel.add(lblRegistoDepatrimonio);

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBounds(22, 44, 881, 168);
		panel.add(panel_1);
		panel_1.setLayout(null);

		txtId = new JTextField();
		txtId.setForeground(new Color(255, 0, 0));
		txtId.setBounds(62, 4, 93, 34);
		panel_1.add(txtId);
		txtId.setEditable(false);
		txtId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Descricao");
		lblNewLabel_1.setBounds(464, 3, 70, 34);
		panel_1.add(lblNewLabel_1);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(559, 4, 310, 34);
		panel_1.add(txtDescricao);
		txtDescricao.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Valor");
		lblNewLabel_2.setBounds(183, 49, 49, 34);
		panel_1.add(lblNewLabel_2);

		txtValor = new JTextField();
		txtValor.setBounds(248, 50, 201, 34);
		panel_1.add(txtValor);
		txtValor.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setBounds(2, 104, 55, 34);
		panel_1.add(lblNewLabel_3);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(2, 8, 14, 15);
		panel_1.add(lblId);

		JLabel lblNewLabel_8 = new JLabel("Tipo");
		lblNewLabel_8.setBounds(186, 95, 63, 34);
		panel_1.add(lblNewLabel_8);
		cboTipo = new JComboBox<>();
		cboTipo.setBounds(248, 96, 201, 37);
		panel_1.add(cboTipo);

		cboStatus = new JComboBox<String>();
		cboStatus.setBounds(68, 109, 87, 34);
		panel_1.add(cboStatus);

		JLabel lblNewLabel_2_1 = new JLabel("Qty");
		lblNewLabel_2_1.setBounds(2, 50, 70, 34);
		panel_1.add(lblNewLabel_2_1);

		txtQty = new JTextField();
		txtQty.setColumns(10);
		txtQty.setBounds(62, 50, 93, 34);
		panel_1.add(txtQty);

		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(559, 50, 310, 34);
		panel_1.add(txtTotal);

		JLabel lblNewLabel_2_2 = new JLabel("Total");
		lblNewLabel_2_2.setBounds(474, 49, 55, 34);
		panel_1.add(lblNewLabel_2_2);

		JLabel lblNewLabel_5 = new JLabel("Codigo");
		lblNewLabel_5.setBounds(173, 4, 76, 34);
		panel_1.add(lblNewLabel_5);

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(250, 4, 196, 34);
		panel_1.add(txtCodigo);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyy-MMM-d");
		dateChooser.setBounds(559, 95, 310, 34);
		panel_1.add(dateChooser);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Data");
		lblNewLabel_2_2_1.setBounds(479, 95, 55, 34);
		panel_1.add(lblNewLabel_2_2_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Acções ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(12, 224, 891, 57);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
				JButton btnSave = new JButton("Gravar");
				btnSave.setBounds(316, 12, 117, 25);
				panel_2.add(btnSave);
				
						JButton btnDelete = new JButton("Apagar");
						btnDelete.setBounds(622, 12, 117, 25);
						panel_2.add(btnDelete);
						
								JButton btnLoad = new JButton("Carregar");
								btnLoad.setBounds(762, 12, 117, 25);
								panel_2.add(btnLoad);
								btnLoad.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {

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
		scrollPane.setBounds(20, 330, 881, 291);

		panel.add(scrollPane);

		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setBorder(new LineBorder(UIManager.getColor("List.dropLineColor")));
		table.setBackground(new Color(255, 255, 255));
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					selection();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				 "ID", "Codigo", "Descricao", "Qty", "Valor", "Total", "Tipo", "Requisicao"
			}
		) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				 false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(15);
		scrollPane.setViewportView(table);

		txtSearch = new JTextField();
		txtSearch.setBounds(20, 284, 723, 34);
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setToolTipText("");
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtSearch.getText().isEmpty()) {
					List<Patrimonio> patrimonios = patrimonioDao.findAll(txtSearch.getText(),empresa.getId());
					showInfo(patrimonios);
				} else
					showpatrimonio();

			}
		});
		txtSearch.setColumns(10);
		panel.add(txtSearch);

		JLabel lblNewLabel = new JLabel("Pesquisar");
		lblNewLabel.setBounds(765, 286, 123, 32);
		panel.add(lblNewLabel);
		/*----------------*/
		patrimonioDao = new PatrimonioDao();
		empresa=FrmLogin.empresa;
		fillStatus();
		fillTipo();
		showpatrimonio();
		

	}

	private void fillStatus() {
		List<String> categorias = new ArrayList<>();
		categorias.add("Activo");
		categorias.add("Inactivo");
		for (String string : categorias)
			cboStatus.addItem(string);
	}

	private void fillTipo() {
		List<String> tipos = new ArrayList<>();
		tipos.add("Activo");
		tipos.add("Passivo");

		for (String string : tipos)
			cboTipo.addItem(string);
	}

	/*------------------------------------------------*/
	private void save() {

		try {
			Patrimonio patrimonio = new Patrimonio();
			String ID = txtId.getText();
			String nome = txtDescricao.getText();
			patrimonio.setEmpresa(empresa);
			if (!nome.isEmpty()) {

				patrimonio.setValor(Double.valueOf(txtValor.getText()));
				patrimonio.setCodigo(txtCodigo.getText());
				patrimonio.setQty(Integer.valueOf(txtQty.getText()));
				patrimonio.setDescricao(txtDescricao.getText());
				patrimonio.setStatus(cboStatus.getSelectedItem().toString());
				patrimonio.setSubTotal(Double.valueOf(txtQty.getText()) * Integer.valueOf(txtQty.getText()));
				patrimonio.setTipo(cboTipo.getSelectedItem().toString());
				// 2022-03-21
				SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
				String date = sdf.format(dateChooser.getDate());
				patrimonio.setDataAquisicao(LocalDate.parse(date));

				if (ID.equals("")) {
					patrimonio.setId(null);
				} else {
					patrimonio.setId(Long.valueOf(ID));
				}

				patrimonioDao.save(patrimonio);
				
				JOptionPane.showMessageDialog(rootPane, " patrimonio Registada com exito");
				showpatrimonio();
				clear();
			} else {
				JOptionPane.showMessageDialog(null, "Introduza uma descricao da patrimonio");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro, Algo estranho ocorreu");
			e.printStackTrace();
		}

	}

	
	
	private void showpatrimonio() {
	    if (empresa != null) {
	        List<Patrimonio> patrimonios = patrimonioDao.findAll(empresa.getId());
	        showInfo(patrimonios);
	    } else {
	        // Lida com o caso em que empresa é nula
	        //System.out.println("A variável 'empresa' é nula. Não é possível buscar patrimônios.");
	        // ou lançar uma exceção, dependendo do comportamento desejado
	    }
	}


	private void showInfo(List<Patrimonio> patrimonios) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] row = new Object[8];
		model.setRowCount(0);// empty the table
		for (int i = 0; i < patrimonios.size(); i++) {
			// ID", "Codigo", "Descricao", "Qty", "Valor", "Total", "Tipo", "Requisicao
			row[0] = patrimonios.get(i).getId();
			row[1] = patrimonios.get(i).getCodigo();
			row[2] = patrimonios.get(i).getDescricao();
			row[3] = patrimonios.get(i).getQty();
			row[4] = patrimonios.get(i).getValor();
			row[5] = patrimonios.get(i).getSubTotal();
			row[6] = patrimonios.get(i).getTipo();
			row[7] = patrimonios.get(i).getDataAquisicao();

			model.addRow(row);
		}

	}

	/*-------------------------------------*/
	private void selection() throws ParseException {
		int selectedRow = table.getSelectedRow();
		if (selectedRow >= 0) {
			Object id = table.getValueAt(selectedRow, 0);
			Object codigo = table.getValueAt(selectedRow, 1);
			Object descricao = table.getValueAt(selectedRow, 2);
			Object qty = table.getValueAt(selectedRow, 3);
			Object valor = table.getValueAt(selectedRow, 4);
			Object total = table.getValueAt(selectedRow, 5);
			Object tipo = table.getValueAt(selectedRow, 6);
			Object requisicao = table.getValueAt(selectedRow, 7);

			txtId.setText(id.toString());
			txtCodigo.setText(codigo.toString());
			txtDescricao.setText(descricao.toString());
			txtQty.setText(qty.toString());
			txtValor.setText(valor.toString());
			txtTotal.setText(total.toString());
			cboTipo.removeAllItems();
			cboTipo.addItem(tipo.toString());
			String data2=requisicao.toString();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateFormat.parse(data2);
			dateChooser.setDate(date);
		}

	}

	private void clear() {
		txtId.setText("");
		txtDescricao.setText("");
		txtValor.setText("");
		txtCodigo.setText("");
		txtQty.setText("");
		txtTotal.setText("");

	}

	private void delete() {
		if (!txtId.getText().isEmpty()) {
			int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?",
					"Confirm Deletion", JOptionPane.YES_NO_OPTION);
			if (confirmation == JOptionPane.YES_OPTION) {
				patrimonioDao.deleteById(Long.valueOf(txtId.getText()));
				showpatrimonio();
				clear();
			} else {
				JOptionPane.showMessageDialog(table, "Selecione a linha na tabela");
			}

		}

	}
}
