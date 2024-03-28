package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.meldev.dao.UtilizadorDao;
import com.meldev.model.Empresa;
import com.meldev.model.Utilizador;

public class FrmLogin {

    private JFrame frmLogin;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private String username, password;
    private JLabel lblLogado;
    public static Long idUsuario = 0L;// it will be used in other class
    private String status = "inactivo";
    public static String perfil = "s";
    public static double iva = 0;
    public static Empresa empresa = null;
    public static String userName = "";
    public UtilizadorDao utilizadorDao;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmLogin window = new FrmLogin();
                    window.frmLogin.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FrmLogin() {
        initialize();
        utilizadorDao = new UtilizadorDao();

    }

    private void initialize() {

        frmLogin = new JFrame();
        frmLogin.getContentPane().setBackground(Color.WHITE);
        frmLogin.setBackground(Color.WHITE);
        frmLogin.setResizable(false);
        frmLogin.getContentPane().setForeground(Color.WHITE);
        frmLogin.setForeground(UIManager.getColor("CheckBox.darkShadow"));
        frmLogin.setTitle("Login");
        frmLogin.setBounds(100, 100, 600, 400);
        frmLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmLogin.setLocationRelativeTo(null);
        frmLogin.getContentPane().setLayout(null);
        frmLogin.setUndecorated(true);

        lblLogado = new JLabel("");
        lblLogado.setFont(new Font("Dialog", Font.BOLD, 14));
        lblLogado.setBackground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
        lblLogado.setForeground(UIManager.getColor("Button.focus"));
        lblLogado.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogado.setBounds(280, 45, 285, 27);
        frmLogin.getContentPane().add(lblLogado);

        JButton btnExit = new JButton("X");
        btnExit.setForeground(Color.WHITE);
        btnExit.setBackground(Color.BLUE);
        btnExit.setBounds(545, 12, 43, 27);
        frmLogin.getContentPane().add(btnExit);
        btnExit.setFont(new Font("DejaVu Serif", Font.PLAIN, 12));

        // ---------------------------------------LOGIN
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(364, 346, 138, 31);
        frmLogin.getContentPane().add(btnLogin);
        btnLogin.setFont(new Font("DejaVu Serif", Font.PLAIN, 12));

        txtPassword = new JPasswordField();
        txtPassword.setBounds(302, 226, 273, 31);
        frmLogin.getContentPane().add(txtPassword);
        txtPassword.setFont(new Font("DejaVu Serif", Font.BOLD, 14));

        txtUsername = new JTextField();
        txtUsername.setBounds(302, 126, 274, 32);
        frmLogin.getContentPane().add(txtUsername);
        txtUsername.setFont(new Font("DejaVu Serif", Font.BOLD, 14));
        txtUsername.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Username");
        lblNewLabel_1.setBounds(302, 84, 86, 33);
        frmLogin.getContentPane().add(lblNewLabel_1);
        lblNewLabel_1.setFont(new Font("DejaVu Serif", Font.PLAIN, 14));

        JLabel lblNewLabel_2 = new JLabel("Password");
        lblNewLabel_2.setBounds(302, 191, 82, 23);
        frmLogin.getContentPane().add(lblNewLabel_2);
        lblNewLabel_2.setFont(new Font("DejaVu Serif", Font.PLAIN, 14));

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setForeground(Color.WHITE);
        panel.setBounds(12, 12, 250, 376);
        frmLogin.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBackground(new Color(51, 153, 255));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon(FrmLogin.class.getResource("/resources/icons8-user-100.png")));
        lblNewLabel.setBounds(12, 12, 226, 352);
        panel.add(lblNewLabel);
        btnLogin.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {
                try {

                    username = txtUsername.getText();
                    password = txtPassword.getText();
                    Optional<Utilizador> utilizador = utilizadorDao.findByName(username);

                    if (utilizador.isPresent()) {
                        username = utilizador.get().getUsername();
                        password = utilizador.get().getPassword();
                        idUsuario = utilizador.get().getId();
                        status = utilizador.get().getStatus();
                        perfil = utilizador.get().getRole().getNome();
                        iva = utilizador.get().getEmpresa().getIva();
                        empresa = utilizador.get().getEmpresa();
                        userName = utilizador.get().getUsername() + " Perfil: " + utilizador.get().getRole().getNome();

                        // when logged as Admin
                        if (perfil.equalsIgnoreCase("administrador") && (status.equalsIgnoreCase("Activo"))
                                && password.equals(txtPassword.getText())) {
                            lblLogado.setText("Succesfully Logged");
                            lblLogado.setForeground(Color.GREEN);
                            txtUsername.setText(null);
                            txtPassword.setText(null);
                            FrmMenu.main(null); // CHAMA O FORMULARIO DE MENU
                            frmLogin.dispose();

                        } else {
                            lblLogado.setText("Utilizador Inactivo Ou senha Incorrenta");
                            lblLogado.setForeground(Color.BLUE);

                        }
                        // When is a Standard
                        if (perfil.equalsIgnoreCase("NORMAL") && (status.equalsIgnoreCase("Activo"))
                                && password.equals(txtPassword.getText())) {

                            lblLogado.setForeground(Color.GREEN);
                            lblLogado.setText("Succesfully Logged");
                            txtUsername.setText(null);
                            txtPassword.setText(null);
                            FrmMenu.main(null); // CHAMA O FORMULARIO DE MENU
                            frmLogin.dispose();
                        } else {
                            lblLogado.setText("Utilizador Inactivo Ou senha Incorrenta");
                            lblLogado.setForeground(Color.BLUE);
                        }

                        if (perfil.equalsIgnoreCase("VENDA") && (status.equalsIgnoreCase("Activo"))
                                && password.equals(txtPassword.getText())) {

                            lblLogado.setForeground(Color.GREEN);
                            lblLogado.setText("Succesfully Logged");
                            txtUsername.setText(null);
                            txtPassword.setText(null);
                            FrmMenu.main(null); // CHAMA O FORMULARIO DE MENU
                            frmLogin.dispose();
                        } else {
                            lblLogado.setText("Utilizador Inactivo Ou senha Incorrenta");
                            lblLogado.setForeground(Color.BLUE);
                        }
                        // ending of logged as Standard

                    } else {

                        lblLogado.setText("Utilizador Inexistente!");
                        lblLogado.setForeground(Color.RED);

                    }
                } catch (Exception exception) {
                    JOptionPane.showInternalMessageDialog(null, "Ocorreu em erro " + exception);

                }

            }
// -----------------------------------------------------------------------------------------------------------------------
        });
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(frmLogin, "Confirm if you want to exit", "INFORMACAO",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                    frmLogin.dispose();
                }
            }
        });

    }
}
