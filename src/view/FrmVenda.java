package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.meldev.dao.ClienteDao;
import com.meldev.dao.DocumentoDao;
import com.meldev.dao.ItemsPedidoDao;
import com.meldev.dao.PedidoDao;
import dao.StockDao;
import com.meldev.model.Cliente;
import com.meldev.model.Documento;
import com.meldev.model.Empresa;
import com.meldev.model.ItemsPedidos;
import com.meldev.model.Pedido;
import model.Producto;
import com.meldev.model.Stock;
import com.meldev.model.Utilizador;

public class FrmVenda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtClienteCodigo;
	private JTextField txtClienteNome;
	private JTextField txtClienteNuit;
	private JTextField txtCodigoProducto;
	private JTextField txtArtigoProducto;
	private JTextField txtDescricaoProducto;
	private JTextField txtCategoria;
	private JTextField txtQty;
	private JTextField txtVenda;
	private JTextField txtSubtotal;
	private JTable table;
	private JTextField txtSubTotal_venda;
	private JTextField txtIva;
	private JTextField txtTotal;
	private JTextField textField_3;
	private JTextField txtDesconto;
	private StockDao stockDao;
	private ClienteDao clienteDao;
	private PedidoDao pedidoDao;
	private ItemsPedidoDao itemsPedidoDao;
	private DocumentoDao documentoDao;
	private Cliente cliente;
	private Long idProducto = 0L;
	private Double total = 0.0;
	private Empresa empresa = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmVenda frame = new FrmVenda();
					frame.setLocationRelativeTo(null);
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
	public FrmVenda() {
		setTitle("VENDA");
		setForeground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 953, 704);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("PAINEL DE VENDA");
		lblNewLabel.setBounds(12, 0, 907, 30);
		lblNewLabel.setForeground(new Color(102, 153, 204));
		lblNewLabel.setBackground(new Color(102, 153, 153));
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(12, 30, 907, 93);
		panel.setBorder(new TitledBorder(null, "Dados do Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Codigo");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel_1.setBounds(5, 14, 204, 30);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel_2.setBounds(290, 14, 309, 30);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Nuit");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Georgia", Font.BOLD, 14));
		lblNewLabel_3.setBounds(641, 14, 254, 30);
		panel.add(lblNewLabel_3);

		txtClienteCodigo = new JTextField();
		txtClienteCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtClienteCodigo.setForeground(new Color(255, 0, 0));
		txtClienteCodigo.setFont(new Font("Georgia", Font.BOLD, 14));
		txtClienteCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtClienteCodigo.getText().isEmpty()) {

					fillCliente();
				}
			}
		});
		txtClienteCodigo.setBounds(5, 45, 204, 30);
		panel.add(txtClienteCodigo);
		txtClienteCodigo.setColumns(10);

		txtClienteNome = new JTextField();
		txtClienteNome.setEditable(false);
		txtClienteNome.setBounds(290, 45, 309, 30);
		panel.add(txtClienteNome);
		txtClienteNome.setColumns(10);

		txtClienteNuit = new JTextField();
		txtClienteNuit.setEditable(false);
		txtClienteNuit.setBounds(641, 45, 261, 30);
		panel.add(txtClienteNuit);
		txtClienteNuit.setColumns(10);

		JButton btnCliente = new JButton("");
		btnCliente.setIcon(new ImageIcon(FrmVenda.class.getResource("/resources/icons8-eye-24.png")));
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCliente.main(null);
			}
		});
		btnCliente.setBounds(214, 47, 44, 25);
		panel.add(btnCliente);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 133, 903, 160);
		panel_1.setBorder(
				new TitledBorder(null, "Detalhes do Artigo" + "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Codigo");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(5, 18, 84, 30);
		panel_1.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Georgia", Font.BOLD, 14));

		JLabel lblNewLabel_4_1 = new JLabel("Artigo");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setBounds(182, 17, 187, 30);
		panel_1.add(lblNewLabel_4_1);
		lblNewLabel_4_1.setFont(new Font("Georgia", Font.BOLD, 14));

		JLabel lblNewLabel_4_2 = new JLabel("Descricao");
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setBounds(394, 17, 299, 30);
		panel_1.add(lblNewLabel_4_2);
		lblNewLabel_4_2.setFont(new Font("Georgia", Font.BOLD, 14));

		JLabel lblNewLabel_4_3 = new JLabel("Categoria");
		lblNewLabel_4_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_3.setBounds(719, 17, 172, 30);
		panel_1.add(lblNewLabel_4_3);
		lblNewLabel_4_3.setFont(new Font("Georgia", Font.BOLD, 14));

		txtCodigoProducto = new JTextField();
		txtCodigoProducto.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigoProducto.setForeground(new Color(255, 0, 0));
		txtCodigoProducto.setFont(new Font("Georgia", Font.BOLD, 14));
		txtCodigoProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!txtCodigoProducto.getText().isEmpty()) {
					fillArtigo(txtCodigoProducto.getText());

				}
			}
		});
		txtCodigoProducto.setBounds(5, 50, 130, 30);
		panel_1.add(txtCodigoProducto);
		txtCodigoProducto.setColumns(10);

		txtArtigoProducto = new JTextField();
		txtArtigoProducto.setEditable(false);
		txtArtigoProducto.setBounds(172, 50, 197, 30);
		panel_1.add(txtArtigoProducto);
		txtArtigoProducto.setColumns(10);

		txtDescricaoProducto = new JTextField();
		txtDescricaoProducto.setEditable(false);
		txtDescricaoProducto.setBounds(394, 50, 299, 30);
		panel_1.add(txtDescricaoProducto);
		txtDescricaoProducto.setColumns(10);

		txtCategoria = new JTextField();
		txtCategoria.setEditable(false);
		txtCategoria.setBounds(719, 50, 179, 30);
		panel_1.add(txtCategoria);
		txtCategoria.setColumns(10);

		JLabel lblNewLabel_4_4 = new JLabel("Qty");
		lblNewLabel_4_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_4.setBounds(5, 93, 122, 30);
		panel_1.add(lblNewLabel_4_4);
		lblNewLabel_4_4.setFont(new Font("Georgia", Font.BOLD, 14));

		txtQty = new JTextField();
		txtQty.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					if (!(txtVenda.getText().isEmpty() && txtQty.getText().isEmpty())) {
						Integer qty = Integer.parseInt(txtQty.getText());
						Double venda = Double.parseDouble(txtVenda.getText());
						Double factor = venda * qty;
						txtSubtotal.setText(factor + "");
					} else {
						txtSubtotal.setText("");

					}
				} catch (NumberFormatException ee) {
					JOptionPane.showMessageDialog(txtQty, "Apenas Numeros");

				}
			}
		});
		txtQty.setBounds(5, 125, 130, 30);
		panel_1.add(txtQty);
		txtQty.setColumns(10);

		JLabel lblNewLabel_4_1_1 = new JLabel("Preco Unitario");
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_1.setBounds(174, 92, 120, 30);
		panel_1.add(lblNewLabel_4_1_1);
		lblNewLabel_4_1_1.setFont(new Font("Georgia", Font.BOLD, 14));

		txtVenda = new JTextField();
		txtVenda.setEditable(false);
		txtVenda.setBounds(172, 125, 130, 30);
		panel_1.add(txtVenda);
		txtVenda.setColumns(10);

		txtSubtotal = new JTextField();
		txtSubtotal.setForeground(new Color(255, 0, 0));
		txtSubtotal.setFont(new Font("Dialog", Font.BOLD, 14));
		txtSubtotal.setEditable(false);
		txtSubtotal.setBounds(314, 125, 130, 30);
		panel_1.add(txtSubtotal);
		txtSubtotal.setColumns(10);

		JLabel lblNewLabel_4_1_1_1 = new JLabel("Sub-Total");
		lblNewLabel_4_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_1_1.setBounds(320, 92, 120, 30);
		panel_1.add(lblNewLabel_4_1_1_1);
		lblNewLabel_4_1_1_1.setFont(new Font("Georgia", Font.BOLD, 14));

		JButton btnAdd = new JButton("+");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (idProducto != 0)
					addRow();

			}
		});
		btnAdd.setBounds(456, 127, 44, 25);
		panel_1.add(btnAdd);

		JButton btnRemove = new JButton("x");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeRow();
			}
		});
		btnRemove.setBounds(512, 127, 44, 25);
		panel_1.add(btnRemove);

		JButton btnConcluir = new JButton("concluir");
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				savePedido();

			}
		});
		btnConcluir.setBounds(568, 125, 96, 25);
		panel_1.add(btnConcluir);

		JButton btnArtigo = new JButton("");
		btnArtigo.setIcon(new ImageIcon(FrmVenda.class.getResource("/resources/icons8-eye-24.png")));
		btnArtigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmStock.main(null);
			}
		});
		btnArtigo.setBounds(91, 21, 44, 25);
		panel_1.add(btnArtigo);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(17, 310, 907, 313);
		panel_2.setBorder(
				new TitledBorder(null, "Carrinho de Compra", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 17, 897, 197);
		panel_2.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Artigo", "Descricao", "Qty", "Preco", "Sub-Total" }) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(15);
		table.getColumnModel().getColumn(4).setPreferredWidth(10);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_4_4_1 = new JLabel("Sub-Total");
		lblNewLabel_4_4_1.setBounds(616, 220, 102, 28);
		panel_2.add(lblNewLabel_4_4_1);
		lblNewLabel_4_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_4_1.setFont(new Font("Georgia", Font.BOLD, 14));

		JLabel lblNewLabel_4_4_2 = new JLabel("IVA");
		lblNewLabel_4_4_2.setBounds(616, 248, 102, 29);
		panel_2.add(lblNewLabel_4_4_2);
		lblNewLabel_4_4_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_4_2.setFont(new Font("Georgia", Font.BOLD, 14));

		JLabel lblNewLabel_4_4_3 = new JLabel("Total");
		lblNewLabel_4_4_3.setBounds(636, 278, 78, 29);
		panel_2.add(lblNewLabel_4_4_3);
		lblNewLabel_4_4_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_4_3.setFont(new Font("Georgia", Font.BOLD, 14));

		txtSubTotal_venda = new JTextField();
		txtSubTotal_venda.setBounds(717, 220, 185, 30);
		panel_2.add(txtSubTotal_venda);
		txtSubTotal_venda.setEditable(false);
		txtSubTotal_venda.setColumns(10);

		txtIva = new JTextField();
		txtIva.setBounds(717, 248, 185, 30);
		panel_2.add(txtIva);
		txtIva.setEditable(false);
		txtIva.setColumns(10);

		txtTotal = new JTextField();
		txtTotal.setBounds(717, 278, 185, 30);
		panel_2.add(txtTotal);
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);

		JLabel lblNewLabel_4_3_1 = new JLabel("Observacao");
		lblNewLabel_4_3_1.setBounds(10, 218, 378, 30);
		panel_2.add(lblNewLabel_4_3_1);
		lblNewLabel_4_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_3_1.setFont(new Font("Georgia", Font.BOLD, 14));

		textField_3 = new JTextField();
		textField_3.setBounds(5, 253, 388, 54);
		panel_2.add(textField_3);
		textField_3.setColumns(10);

		txtDesconto = new JTextField();
		txtDesconto.setBounds(422, 277, 130, 30);
		panel_2.add(txtDesconto);
		txtDesconto.setColumns(10);

		JLabel lblNewLabel_4_1_2 = new JLabel("Desconto");
		lblNewLabel_4_1_2.setBounds(432, 244, 96, 30);
		panel_2.add(lblNewLabel_4_1_2);
		lblNewLabel_4_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_2.setFont(new Font("Georgia", Font.BOLD, 14));
		/**/
		clienteDao = new ClienteDao();
		stockDao = new StockDao();
		pedidoDao = new PedidoDao();
		itemsPedidoDao = new ItemsPedidoDao();
		documentoDao = new DocumentoDao();
		empresa = FrmLogin.empresa;

	}

	/* fill client */
	private void fillCliente() {
		Optional<Cliente> clienteOptional = clienteDao.findByClienteCodigoOrNome(txtClienteCodigo.getText());
		if (clienteOptional.isPresent()) {
			cliente = clienteOptional.get();
			txtClienteNuit.setText(cliente.getNuit());
			txtClienteNome.setText(cliente.getNome());

		} else {
			txtClienteNuit.setText("Inexistente");
			txtClienteNome.setText("Inexistente");
		}

	}

	/*------------------------------------------------------------------------------------------------------------------------------------------*/
	private void fillArtigo(String codigo) {
		Optional<Stock> stockOptional = stockDao.findByProductoCodigo(codigo);
		if (stockOptional.isPresent()) {
			Stock stock = stockOptional.get();
			txtArtigoProducto.setText(stock.getProducto().getNome());
			txtDescricaoProducto.setText(stock.getProducto().getDescricao());
			txtCategoria.setText(stock.getProducto().getCategoria().getNome());
			txtArtigoProducto.setText(stock.getProducto().getNome());
			txtVenda.setText(stock.getPrecoVenda() + "");
			idProducto = stock.getProducto().getId();
		} else {
			txtArtigoProducto.setText("");
			txtDescricaoProducto.setText("");
			txtQty.setText("");
			txtCategoria.setText("");
			txtVenda.setText("");
			txtSubtotal.setText("");

		}

	}

	/*-------------------------------------------------------------------------------------------------------------------------------------------*/
	/**/

	private void addRow() {
		try {
			// Get data from text fields
			String idArtigo = idProducto + "";
			String artigo = txtArtigoProducto.getText();
			String descricao = txtDescricaoProducto.getText();
			String qty = txtQty.getText();
			String venda = txtVenda.getText();
			String subTotal = txtSubtotal.getText();

			// Get the table model
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			// Check if the same "Id Artigo" already exists in the table
			boolean idArtigoExists = false;
			for (int i = 0; i < model.getRowCount(); i++) {
				String existingIdArtigo = model.getValueAt(i, 0).toString();
				if (idArtigo.equals(existingIdArtigo)) {
					idArtigoExists = true;
					break;
				}
			}

			// Add the row to the table if the "Id Artigo" doesn't already exist
			if (!idArtigoExists && !(descricao.isEmpty() && subTotal.isEmpty() && venda.isEmpty() && qty.isEmpty()
					&& idArtigo.isEmpty())) {
				// Create an array to hold the data
                                                                    
				Object[] rowData = { idArtigo, artigo, descricao, qty, venda, subTotal };
				if (!qty.isEmpty())
					model.addRow(rowData);

				total = total + Double.valueOf(subTotal);

				txtTotal.setText(total + "");
				txtSubTotal_venda.setText(total + "");
                                                                 
			} else
				JOptionPane.showMessageDialog(table, "Producto existente");
			/* DEPOIS DE ADICIONAR A LINHA */
			txtQty.setText("");
			txtSubtotal.setText("");
			txtVenda.setText("");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(txtSubtotal, "Verifica o preenchimento dos dados");
		}
	}

	/*---------------------------------------------------------------------*/
	private void removeRow() {
		int[] selectedRows = table.getSelectedRows();
		for (int i = selectedRows.length - 1; i >= 0; i--) {
			int selectedRow = selectedRows[i];
			total -= Double.parseDouble(table.getValueAt(selectedRow, 5).toString()); // Subtract subtotal from total
			// tableModel.removeRow(selectedRow);
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.removeRow(selectedRow);
			txtSubTotal_venda.setText(total + "");
			txtTotal.setText(total + "");
		}

	}

	/*----------------------------------------------------------------------*/
	private void savePedido() {
		try {
			Optional<Documento> documento = documentoDao.findByName("VD");
			// cliente
			Double desconto = Double.valueOf("0");
			Double total = Double.valueOf(txtTotal.getText());
			Double subTotal = Double.valueOf(txtTotal.getText());
			// Double iva=Double.valueOf("0");
			Long userId = FrmLogin.idUsuario;
			Utilizador utilizador = new Utilizador();
			utilizador.setId(userId);
			Pedido pedido = new Pedido();
			pedido.setUtilizador(utilizador);
			pedido.setCliente(cliente);
			//pedido.setDocumento(documento.get());
			pedido.setDescontoComercial(desconto);
			pedido.setSubTotal(subTotal);
			pedido.setEmpresa(empresa);
			pedido.setTotal(total);
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			if (pedido.getCliente() != null && modelo.getRowCount() > 0) {
				Pedido pedidoObj = pedidoDao.save(pedido);
                                                                            System.out.print(pedidoObj.toString());
				saveItemsPedido(pedidoObj);
				clean();
			} else
				JOptionPane.showMessageDialog(table, "Garanta que o carrinho e o Cliente esteja Selecionado");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(txtSubtotal, "Selecione o cliente " + e.getMessage());
		} catch (InputMismatchException e) {
			JOptionPane.showMessageDialog(txtSubtotal,
					"Garanta que o carrinho e o Cliente esteja Selecionado. " + e.getMessage());
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(txtSubtotal,
					"Garanta que o carrinho e o Cliente esteja Selecionado " + e.getMessage());
		} catch (Exception e) {
                    e.printStackTrace();
			JOptionPane.showMessageDialog(txtSubtotal, "Stock insufuciente! " + e.getMessage());
		}

	}

	private void clean() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		txtCodigoProducto.setText("");
		txtClienteCodigo.setText("");
		txtTotal.setText("");
		txtSubTotal_venda.setText("");
		txtArtigoProducto.setText("");
		txtCategoria.setText("");
		txtDescricaoProducto.setText("");
		txtClienteNome.setText("");
		txtClienteNuit.setText("");
		total = 0.0;

	}

	/*----------------------------------------------------------------------*/
	private void saveItemsPedido(Pedido pedido) {
		List<ItemsPedidos> list = new ArrayList<ItemsPedidos>();

		for (int row = 0; row < table.getRowCount(); row++) {
			ItemsPedidos itemsPedido = new ItemsPedidos();

			Object productoObj = table.getValueAt(row, 0);
			Object qtyObj = table.getValueAt(row, 3);
			Object vendaObj = table.getValueAt(row, 4);
			Object subTotalObj = table.getValueAt(row, 5);

			if (productoObj != null && qtyObj != null && vendaObj != null && subTotalObj != null) {
				Producto producto = new Producto();
				producto.setId(Long.valueOf(productoObj.toString()));
				itemsPedido.setProducto(producto);

				itemsPedido.setQuantidade(Integer.valueOf(qtyObj.toString()));
				itemsPedido.setPrecoUnitario(Double.valueOf(vendaObj.toString()));
				itemsPedido.setSubTotal(Double.valueOf(subTotalObj.toString()));

				itemsPedido.setPedido(pedido);

				list.add(itemsPedido);
			}
		}

		itemsPedidoDao.saveAll(list);
		JOptionPane.showMessageDialog(contentPane, "Venda Registada!");
		// after this a trigger is called in the DB for updating qty of product in
		// tbl_stock
	}
	/*--------------------------------------------*/

}
