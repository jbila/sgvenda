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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.swing.ImageIcon;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.meldev.dao.ItemsPedidoDao;
import com.meldev.dao.PedidoDao;
import com.meldev.model.Empresa;
import com.meldev.model.ItemsPedidos;
import com.meldev.model.Pedido;
import com.meldev.reports.Reports;
import com.toedter.calendar.JDateChooser;

public class FrmVendas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeCliente;
	private JTable table;
	private JTextField txtSubTotal_venda;
	private JTextField txtIva;
	private JTextField txtTotal;
	private JTextField txtDesconto;
	private PedidoDao pedidoDao;
	private final ItemsPedidoDao itemsPedidoDao;
	private JTable tableDetalhes;
	private String date1, date2;
	private Empresa empresa=null;


	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmVendas frame = new FrmVendas();
					frame.setLocationRelativeTo(null);
                                          frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmVendas() {
		setTitle("VENDA");
		setForeground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1310, 704);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("VENDAS REGISTADAS");
		lblNewLabel.setForeground(new Color(102, 153, 204));
		lblNewLabel.setBackground(new Color(102, 153, 153));
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 0, 921, 30);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Dados de Pesquisa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 30, 907, 93);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Pesquisa Pelo Nome");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel_1.setBounds(5, 14, 204, 30);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Data Inicio");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel_2.setBounds(342, 14, 146, 30);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Data Fim");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel_3.setBounds(510, 14, 151, 30);
		panel.add(lblNewLabel_3);

		txtNomeCliente = new JTextField();
		txtNomeCliente.setForeground(new Color(255, 0, 0));
		txtNomeCliente.setFont(new Font("Georgia", Font.BOLD, 14));
		txtNomeCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtNomeCliente.getText().isEmpty()) {
					List<Pedido> pedidos = pedidoDao.findAll(txtNomeCliente.getText(),empresa.getId());
					showInfo(pedidos);

				} else {
					showPedido();
				}
			}
		});
		txtNomeCliente.setBounds(5, 45, 204, 30);
		panel.add(txtNomeCliente);
		txtNomeCliente.setColumns(10);

		JButton btnCliente = new JButton("");
		btnCliente.setIcon(new ImageIcon(FrmVendas.class.getResource("/resources/icons8-eye-24.png")));
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					Object idPedidoObj = table.getValueAt(selectedRow, 0);
					Long pedido = Long.valueOf(idPedidoObj.toString());
					showItemsPedido(pedido);
				}

			}
		});
		btnCliente.setBounds(214, 47, 64, 25);
		panel.add(btnCliente);

		JDateChooser dateChooserInicio = new JDateChooser();
		dateChooserInicio.setBounds(342, 45, 146, 30);
		panel.add(dateChooserInicio);

		JDateChooser dateChooserFinal = new JDateChooser();
		dateChooserFinal.setBounds(520, 45, 136, 30);
		panel.add(dateChooserFinal);

		JButton btnCliente_1 = new JButton("");
		btnCliente_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
				date1 = sdf.format(dateChooserInicio.getDate());
				date2 = sdf.format(dateChooserFinal.getDate());
				LocalDate startDate = LocalDate.parse(date1);
				LocalDate endDate = LocalDate.parse(date2);
				List<Pedido> pedidos = pedidoDao.findAllByDates(startDate, endDate);
				showInfo(pedidos);
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(btnCliente_1, "Selecione as Data:");
				}

			}
		});
		btnCliente_1.setIcon(new ImageIcon(FrmVendas.class.getResource("/resources/icons8-eye-24.png")));
		btnCliente_1.setBounds(668, 45, 64, 25);
		panel.add(btnCliente_1);

		JButton btnPrint = new JButton("");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
					date1 = sdf.format(dateChooserInicio.getDate());
					date2 = sdf.format(dateChooserFinal.getDate());
					Reports reports;
					reports = new Reports();

					reports.relatorioVenda(date1, date2);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(btnPrint, "Selecione as Data:");
				}

			}

		});
		btnPrint.setIcon(new ImageIcon(FrmVendas.class.getResource("/resources/icons8-print-24.png")));
		btnPrint.setBounds(741, 47, 64, 25);
		panel.add(btnPrint);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(
				new TitledBorder(null, "Vendas Efectuadas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(17, 135, 907, 488);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//JOptionPane.showConfirmDialog(null, "sim");
				int selectedRow = table.getSelectedRow();
				if (selectedRow >= 0) {
					Object idPedidoObj = table.getValueAt(selectedRow, 0);
					Long pedido = Long.valueOf(idPedidoObj.toString());
				
					showItemsPedido(pedido);
				}

			}
		});
		scrollPane.setBounds(5, 17, 897, 345);
		panel_2.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Cliente", "Subtotal", "Total", "Desconto", "Utilizador","Data" }) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false,false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(15);
		table.getColumnModel().getColumn(4).setPreferredWidth(10);
		table.getColumnModel().getColumn(6).setPreferredWidth(10);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_4_4_1 = new JLabel("Sub-Total");
		lblNewLabel_4_4_1.setBounds(615, 388, 102, 28);
		panel_2.add(lblNewLabel_4_4_1);
		lblNewLabel_4_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_4_1.setFont(new Font("Georgia", Font.BOLD, 14));

		JLabel lblNewLabel_4_4_2 = new JLabel("IVA");
		lblNewLabel_4_4_2.setBounds(616, 416, 102, 29);
		panel_2.add(lblNewLabel_4_4_2);
		lblNewLabel_4_4_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_4_2.setFont(new Font("Georgia", Font.BOLD, 14));

		JLabel lblNewLabel_4_4_3 = new JLabel("Total");
		lblNewLabel_4_4_3.setBounds(636, 446, 78, 29);
		panel_2.add(lblNewLabel_4_4_3);
		lblNewLabel_4_4_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_4_3.setFont(new Font("Georgia", Font.BOLD, 14));

		txtSubTotal_venda = new JTextField();
		txtSubTotal_venda.setForeground(new Color(255, 0, 0));
		txtSubTotal_venda.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtSubTotal_venda.setHorizontalAlignment(SwingConstants.CENTER);
		txtSubTotal_venda.setBounds(717, 388, 185, 30);
		panel_2.add(txtSubTotal_venda);
		txtSubTotal_venda.setEditable(false);
		txtSubTotal_venda.setColumns(10);

		txtIva = new JTextField();
		txtIva.setForeground(new Color(255, 0, 0));
		txtIva.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtIva.setHorizontalAlignment(SwingConstants.CENTER);
		txtIva.setBounds(717, 416, 185, 30);
		panel_2.add(txtIva);
		txtIva.setEditable(false);
		txtIva.setColumns(10);

		txtTotal = new JTextField();
		txtTotal.setForeground(new Color(255, 0, 0));
		txtTotal.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setBounds(717, 446, 185, 30);
		panel_2.add(txtTotal);
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);

		txtDesconto = new JTextField();
		txtDesconto.setBounds(104, 367, 229, 30);
		panel_2.add(txtDesconto);
		txtDesconto.setColumns(10);

		JLabel lblNewLabel_4_1_2 = new JLabel("Desconto");
		lblNewLabel_4_1_2.setBounds(5, 366, 96, 30);
		panel_2.add(lblNewLabel_4_1_2);
		lblNewLabel_4_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_2.setFont(new Font("Georgia", Font.BOLD, 14));

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(
				new TitledBorder(null, "Detalhes da Venda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(931, 30, 339, 588);
		contentPane.add(panel_3);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 24, 327, 552);
		panel_3.add(scrollPane_1);

		tableDetalhes = new JTable();
		tableDetalhes.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Artigo", "Qty", "Preco" }) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableDetalhes.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableDetalhes.getColumnModel().getColumn(1).setPreferredWidth(20);
		tableDetalhes.getColumnModel().getColumn(2).setPreferredWidth(20);
		scrollPane_1.setViewportView(tableDetalhes);
		/**/
		pedidoDao = new PedidoDao();
		itemsPedidoDao = new ItemsPedidoDao();
		empresa=FrmLogin.empresa;
		showPedido();

	}

	//
	private void showPedido() {
		List<Pedido> pedidos = pedidoDao.findAll(empresa.getId());
		showInfo(pedidos);
	}

	/*-----------------------------------*/
	private void showInfo(List<Pedido> pedidos) {
		txtTotal.setText("");
		txtSubTotal_venda.setText("");
		txtDesconto.setText("");

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] row = new Object[7];

		model.setRowCount(0); // Clear the table

		double total = 0.0;
		double desconto = 0.0;
		double subTotal = 0.0;
	

		for (Pedido pedido : pedidos) {
			row[0] = pedido.getId();
			row[1] = pedido.getCliente().getNome();
			row[2] = pedido.getSubTotal();
			row[3] = pedido.getTotal();
			row[4] = pedido.getDescontoComercial();
			row[5] = pedido.getUtilizador().getUsername();
			row[6] = pedido.getCreated();

			total += Double.valueOf(pedido.getTotal());
			desconto += Double.valueOf(pedido.getDescontoComercial());
			subTotal += Double.valueOf(pedido.getSubTotal());
			

			model.addRow(row);
		}

		txtTotal.setText(String.valueOf(total));
		txtSubTotal_venda.setText(String.valueOf(subTotal));
		txtDesconto.setText(String.valueOf(desconto));
	}

	/*--------------------------------------------------------------------------*/
	private void showItemsPedido(Long idPedido) {
		List<ItemsPedidos> items = itemsPedidoDao.findAllByIdPedido(idPedido);
		DefaultTableModel model = (DefaultTableModel) tableDetalhes.getModel();
		Object[] row = new Object[3];
		model.setRowCount(0); // Clear the table
		for (ItemsPedidos itemsPedido : items) {
			row[0] = itemsPedido.getProducto().getNome();
			row[1] = itemsPedido.getQuantidade();
			row[2] = itemsPedido.getSubTotal();
			model.addRow(row);
		}

	}
}
