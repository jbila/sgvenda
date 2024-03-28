package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.UIManager;

public class FrmRelatorio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRelatorio frame = new FrmRelatorio();
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
	public FrmRelatorio() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 898, 694);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRelatorio = new JLabel("Relatorio");
		lblRelatorio.setBackground(UIManager.getColor("Button.focus"));
		lblRelatorio.setFont(new Font("DejaVu Sans", Font.BOLD | Font.ITALIC, 14));
		lblRelatorio.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelatorio.setBounds(12, 12, 876, 38);
		contentPane.add(lblRelatorio);
	}

}
