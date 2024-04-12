package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.meldev.model.Empresa;

public class FrmMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame FrmMenu;
	private JMenuBar menuBar;
	private Empresa empresa=null;

	public FrmMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmMenu.class.getResource("/resources/icons8-sell-24.png")));
		setBackground(new Color(255, 255, 255));
		getContentPane().setForeground(new Color(255, 255, 255));
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1116, 579);

		menuBar = new JMenuBar();
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setBackground(new Color(102, 153, 204));
		setJMenuBar(menuBar);

		JMenu mnHome = new JMenu("Home");
		mnHome.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-home-24.png")));
		mnHome.setFont(new Font("Courier New", Font.BOLD, 18));
		menuBar.add(mnHome);

		JMenuItem mntmLogOff = new JMenuItem("Log Off");
		mntmLogOff.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-log-out-24.png")));
		mntmLogOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(FrmMenu, "sair do Sistema", "SGV",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					FrmLogin.main(null);
					dispose();

				}
			};

		});

		mnHome.add(mntmLogOff);

		JMenuItem mntmDashboard = new JMenuItem("DashBoard");
		mntmDashboard.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-dashboard-layout-24.png")));
		mnHome.add(mntmDashboard);

		JMenu mnRecursosHumano = new JMenu("Recursos Humanos");
		mnRecursosHumano
				.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-change-employee-female-24.png")));
		mnRecursosHumano.setFont(new Font("Courier New", Font.BOLD, 18));
		menuBar.add(mnRecursosHumano);

		JMenuItem mntmDepartamento = new JMenuItem("Departamento");
		mntmDepartamento.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-category-24.png")));
		mntmDepartamento.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				FrmDepartamento.main(null);
			}
		});
		mnRecursosHumano.add(mntmDepartamento);

		JMenuItem mntmFuncao = new JMenuItem("Função");
		mntmFuncao.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-category-24.png")));
		mntmFuncao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmFuncao.main(null);
			}
		});
		mnRecursosHumano.add(mntmFuncao);

		JMenuItem mntmFuncionario = new JMenuItem("Funcionario");
		mntmFuncionario.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/user.png")));
		mnRecursosHumano.add(mntmFuncionario);

		JMenu mnVenda = new JMenu("Venda");
		mnVenda.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-sell-24.png")));
		mnVenda.setFont(new Font("Courier New", Font.BOLD, 18));
		menuBar.add(mnVenda);

		JMenuItem mntmCategoria = new JMenuItem("Categoria");
		mntmCategoria.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-category-24.png")));
		mntmCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                                                            
				FrmCategoria2.main(null);
                                                                        

			}
		});

		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mntmCliente.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/user.png")));
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCliente.main(null);
			}
		});
		mnVenda.add(mntmCliente);
		mnVenda.add(mntmCategoria);

		JMenuItem mntmFornecedor = new JMenuItem("Fornecedor");
		mntmFornecedor.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/user.png")));
		mntmFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmFornecedor.main(null);
			}
		});
		mnVenda.add(mntmFornecedor);

		JMenuItem mntmProducto = new JMenuItem("Producto");
		mntmProducto.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-product-24.png")));
		mntmProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmProducto.main(null);
			}
		});
		mnVenda.add(mntmProducto);

		JMenuItem mntmPdv = new JMenuItem("PDV");
		mntmPdv.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-purchase-24.png")));
		mntmPdv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmVenda.main(null);
			}
		});
		mnVenda.add(mntmPdv);

		JMenuItem mntmStock = new JMenuItem("Stock");
		mntmStock.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-stock-24.png")));
		mntmStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmStock.main(null);
			}
		});
		mnVenda.add(mntmStock);

		JMenuItem mntmVendas = new JMenuItem("Vendas");
		mntmVendas.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-purchased-24.png")));
		mntmVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmVendas.main(null);
			}
		});
		mnVenda.add(mntmVendas);

		JMenu mnFacturacao = new JMenu("Facturação");
		mnFacturacao.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-invoice-24.png")));
		mnFacturacao.setFont(new Font("Courier New", Font.BOLD, 18));
		menuBar.add(mnFacturacao);

		JMenuItem mntmArtigo = new JMenuItem("Artigo");
		mntmArtigo.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-product-24.png")));
		mnFacturacao.add(mntmArtigo);

		JMenuItem mntmDocumento = new JMenuItem("Documento");
		mntmDocumento.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-file-24.png")));
		mntmDocumento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmDocumento.main(null);
			}
		});
		mnFacturacao.add(mntmDocumento);

		JMenu mnContablidade = new JMenu("Contabilidade");
		mnContablidade.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-accounting-24.png")));
		mnContablidade.setFont(new Font("Courier New", Font.BOLD, 18));
		menuBar.add(mnContablidade);

		JMenuItem mntmPatrimonio = new JMenuItem("Patrimonio");
		mntmPatrimonio.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-company-24.png")));
		mntmPatrimonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmPatrimonio.main(null);
			}
		});
		mnContablidade.add(mntmPatrimonio);

		JMenuItem mntmReceitas = new JMenuItem("Receita");
		mntmReceitas.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-income-24.png")));
		mntmReceitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmReceita.main(null);
			}
		});
		
				JMenuItem mntmTiporeceita = new JMenuItem("TipoReceita");
				mntmTiporeceita.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-category-24.png")));
				mntmTiporeceita.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						FrmTipoReceita.main(null);
					}
				});
				mnContablidade.add(mntmTiporeceita);
		mnContablidade.add(mntmReceitas);

		JMenuItem mntmDespesa = new JMenuItem("Despesa");
		mntmDespesa.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-expenses-24.png")));
		mntmDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FrmDespesa.main(null);
			}
		});
		
				JMenuItem mntmTipoDespesa = new JMenuItem("Tipo Despesa");
				mntmTipoDespesa.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-category-24.png")));
				mntmTipoDespesa.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						FrmTipoDespesa.main(null);
					}
				});
				mnContablidade.add(mntmTipoDespesa);
		mnContablidade.add(mntmDespesa);

		JMenu mnConfiguracao = new JMenu("Configuração");
		mnConfiguracao.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-setting-24.png")));
		mnConfiguracao.setHorizontalAlignment(SwingConstants.RIGHT);
		mnConfiguracao.setFont(new Font("Courier New", Font.BOLD, 18));
		menuBar.add(mnConfiguracao);

		JMenuItem mntmUtilizador = new JMenuItem("Utilizador");
		mntmUtilizador.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/user.png")));
		mntmUtilizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmUtilizador.main(null);
			}
		});
		
		JMenuItem mntmEmpresa = new JMenuItem("Empresa");
		mntmEmpresa.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-dashboard-layout-24.png")));
		mntmEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmEmpresa.main(null);
			}
		});
		mnConfiguracao.add(mntmEmpresa);
		mnConfiguracao.add(mntmUtilizador);

		JMenuItem mntmFormaPagamento = new JMenuItem("Forma de Pagamento");
		mntmFormaPagamento.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-payment-24.png")));
		mntmFormaPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmFormaPagamento.main(null);

			}
		});
		mnConfiguracao.add(mntmFormaPagamento);

		JMenuItem mntmUm = new JMenuItem("UM");
		mntmUm.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-ruller-24.png")));
		mntmUm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmUm.main(null);
			}
			
		});
		
		JMenuItem mntmConta = new JMenuItem("Conta");
		mntmConta.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-dashboard-layout-24.png")));
		mntmConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmConta.main(null);
			}
		});
		mnConfiguracao.add(mntmConta);
		mnConfiguracao.add(mntmUm);
		
		JMenuItem mntmBackup = new JMenuItem("Back-UP");
		mntmBackup.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/icons8-data-base-24.png")));
		mnConfiguracao.add(mntmBackup);
		
		JLabel ulblUser = new JLabel("user:");
		ulblUser.setForeground(Color.WHITE);
		ulblUser.setFont(new Font("Dialog", Font.BOLD, 14));
		menuBar.add(ulblUser);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setForeground(new Color(255, 255, 255));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(FrmMenu.class.getResource("/resources/sales.jpg")));
		panel.add(lblNewLabel, BorderLayout.CENTER);
		if (FrmLogin.perfil.equals("VENDA")) {
			mnConfiguracao.setVisible(false);
			mnRecursosHumano.setVisible(false);
			mnFacturacao.setVisible(false);
			mnContablidade.setVisible(false);
		} else if (FrmLogin.perfil.equals("NORMAL")) {
			mnVenda.setVisible(false);
			mnConfiguracao.setVisible(false);

		} else {

		}
		/*------------------------*/
		empresa=FrmLogin.empresa;
		ulblUser.setText(" Empresa: "+empresa.getNome()+" Utilizador: "+FrmLogin.userName);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMenu frame = new FrmMenu();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
